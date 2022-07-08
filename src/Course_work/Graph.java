package Course_work;

import Course_work.BinaryTree.NodeTree;
import MyLibrary.Stack;
import MyLibrary.QueueList;

public class Graph {
    public int[][] adjMatrix;                   // матриця суміжності
    public int currentVertex;                   // кількість вершин вершин
    public int maxVertex;                       // кількість вершин вершин
    LinkedList<NodeTree> vertexList;            // список вершин
    Stack stack;
    QueueList queue;

    public Graph(int maxVertex) {
        vertexList = new LinkedList<>();
        adjMatrix = new int[maxVertex][maxVertex];
        stack = new Stack(maxVertex);
        queue = new QueueList();
        this.maxVertex = maxVertex;
        currentVertex = 0;
    }

    public void addEdge(int start, int end, int value) {
        adjMatrix[start][end] = value;
    }

    public void addVertex(NodeTree vertex) {
        vertexList.add(vertex);
        currentVertex++;
    }

    public void printMatrix() {
        for (int[] ints : adjMatrix) {
            for (int number : ints)
                System.out.print(number + "\t");
            System.out.println();
        }
    }

    public void displayVertex(int vertex) {
        NodeTree find = getNodeById(vertex);
        if (find != null)
            System.out.printf("Id: %d\tKey: %10s\t\tValue: %s\n", find.id, find.key, find.value);
    }

    /* У список записувати вершини. Якщо currentVertex == shopId, то виводимо*/
    public void dfs(String clientAddress, String shopAddress) {             // обход в глубину
        NodeTree vertexClient = getNodeByValue(clientAddress);   //  вершина клиента по адресу
        NodeTree vertexShop = getNodeByValue(shopAddress);       //  вершина магазина по адресу
        LinkedList<Integer> list = new LinkedList<>();

        if (vertexClient != null && vertexShop != null) {
            int clientId = vertexClient.id;
            int shopId = vertexShop.id;

            vertexClient.isVisited = true;
            list.add(clientId);
            stack.push(clientId);

            while (!stack.isEmpty()) {
                int v = getUnvisitedVertex((Integer) stack.peek());     // getIndex не посещенной смежной веришины
                if (v == -1) {                                          // если не пройденных смежной вершины нет
                    stack.pop();                                        // элемент извлекается из стека
                }
                else if (v == shopId) {                                 // STOP BFS if you find path <Client -> Shop>
                    list.add(v);
                    break;
                }
                else {
                    NodeTree vertex = getNodeById(v);
                    vertex.isVisited = true;
                    list.add(v);
                    stack.push(v);                                      // элемент попадает на вершину стека
                }
            }
            outputPath(list, shopId);
            stack.clear();
            resetVisitedVertex();
        } else System.out.println("Шляху не має");
    }

    public void bfs(String clientAddress, String shopAddress) {               // client -> shop
        NodeTree vertexClient = getNodeByValue(clientAddress);
        NodeTree vertexShop = getNodeByValue(shopAddress);
        LinkedList<Integer> list = new LinkedList<>();

        if (vertexClient != null && vertexShop != null) {
            vertexClient.isVisited = true;                                      // берётся вершина клиента
            int clientId = vertexClient.id;
            int shopId = vertexShop.id;

            list.add(clientId);
            queue.add(clientId);
            int unvisitedId = -1;

            while (!queue.isEmpty()) {
                if (shopId == unvisitedId)       break;
                int vertexId = (Integer) queue.poll();

                while ((unvisitedId = getUnvisitedVertex(vertexId)) != -1) {
                    NodeTree vertex = getNodeById(unvisitedId);
                    vertex.isVisited = true;
                    list.add(unvisitedId);
                    if (unvisitedId == shopId)  break;
                    queue.add(unvisitedId);
                }
            }
            outputPath(list, shopId);
            resetVisitedVertex();
            queue.clear();
        } else System.out.println("Шляху не має");
    }

    public void dijkstra(String clientAddress, String shopAddress) {            // shop -> client
        NodeTree vertexClient = getNodeByValue(clientAddress);       // вершина клієнта за адресою
        NodeTree vertexShop = getNodeByValue(shopAddress);           // вершина магазина за адресою

        if (vertexClient != null && vertexShop != null) {
            int clientId = vertexClient.id;
            int shopId = vertexShop.id;

            int v = adjMatrix.length;
            boolean[] visited = new boolean[v];
            int[] distance = new int[v];
            distance[shopId] = 0;                           // відстань від shop to shop = 0

            for (int i = 0; i < v; i++)                     // заповнення відстані від shopId -> vertex = INFINITY
                if (i != shopId)
                    distance[i] = Integer.MAX_VALUE;

            for (int i = 0; i < v - 1; i++) {
                // Знайти вершину з Min Distance
                int minVertex = findMinVertex(distance, visited);
                visited[minVertex] = true;
                // досліджуємо сусідів
                for (int j = 0; j < v; j++) {
                    if (adjMatrix[minVertex][j] != 0 && !visited[j] && distance[minVertex] != Integer.MAX_VALUE) {
                        int newDist = distance[minVertex] + adjMatrix[minVertex][j];
                        if (newDist < distance[j])
                            distance[j] = newDist;
                    }
                }
            }
            if (distance[clientId] != Integer.MAX_VALUE) {
                System.out.print("From: \t");   displayVertex(shopId);
                System.out.print("To: \t");     displayVertex(clientId);
                System.out.printf("Мінімальний час проходження: %d хв.\n\n", distance[clientId]);
            } else System.out.println("Шляху не має\n");

        } else System.out.println("Шляху не має\n");
    }

    public int findMinVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distance.length; i++)
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex]))
                minVertex = i;
        return minVertex;

    }

    public int getUnvisitedVertex(int v) {
        for (int j = 0; j < currentVertex; j++) {
            NodeTree findVertex = getNodeById(j);
            if (adjMatrix[v][j] != 0 && (findVertex != null && !findVertex.isVisited) ) {
                return j; //возвращает первую найденную вершину
            }
        }
        return -1;
    }

    public void resetVisitedVertex() {          // сброс флагов посещенных вершин
        LinkedList<NodeTree>.Node head = vertexList.getHead();
        while (head != null) {
            head.vertex.isVisited = false;
            head = head.next;
        }
    }

    public boolean removeVertex(int vertexId) {
        NodeTree deleteNode = getNodeById(vertexId);
        if (deleteNode != null) {
            vertexList.remove(deleteNode);         // видаляє вершину зі списка вершин
            for (int i = 0; i < adjMatrix.length; i++)
                for (int j = 0; j < adjMatrix[0].length; j++)
                    if ((i == vertexId || j == vertexId) && adjMatrix[i][j] != 0)
                        adjMatrix[i][j] = 0;
            currentVertex--;
            return true;
        }
        return false;
    }

    public void outputPath(LinkedList<Integer> list, int shopId) {
        if (list.getTail().vertex == shopId) {
            LinkedList<Integer>.Node currentNode = list.getHead();
            while (currentNode != null) {
                displayVertex(currentNode.vertex);
                currentNode = currentNode.next;
            }
        } else System.out.println("Шляху не має");
    }

    public NodeTree getNodeByValue(Object value) {
        LinkedList<NodeTree>.Node currentNode = vertexList.getHead();
        while (currentNode != null) {
            if (currentNode.vertex.value.equals(value))       return currentNode.vertex;
            currentNode = currentNode.next;
        }
        return null;
    }

    public NodeTree getNodeById(int id) {
        LinkedList<NodeTree>.Node currentNode = vertexList.getHead();
        while (currentNode != null) {
            if (currentNode.vertex.id == id)       return currentNode.vertex;
            currentNode = currentNode.next;
        }
        return null;
    }

    public void printVertex() {
        for (int i = 0; i < maxVertex; i++) {
            NodeTree temp = getNodeById(i);
            if (temp != null)
                displayVertex(temp.id);
        }
        System.out.println();
    }

}