package Course_work;

import Course_work.LinkedList.Node;
import Course_work.BinaryTree.NodeTree;
import MyLibrary.Stack;
import MyLibrary.QueueList;

import MyLibrary.JenericLinkedList;

public class Graph {
    public int[][] adjMatrix;       // матрица смежности
    public int currentVertex;       // текущее количество вершин
    int countOfVertexInTree;        // количество рассмотренных вершин в дереве
    LinkedList vertexList;          // массив вершин
    Stack stack;
    QueueList queue;

    public Graph(int maxVertex) {
        vertexList = new LinkedList();
        adjMatrix = new int[maxVertex][maxVertex];
        stack = new Stack(maxVertex*2);
        queue = new QueueList();
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
        Node head = vertexList.getHead();
        while (head != null) {
            if (head.vertex.id == vertex) {
                System.out.println("Id: " + head.vertex.id + "\tKey: " + head.vertex.key + "\tValue: " + head.vertex.value);
                break;
            }
            head = head.next;
        }
    }

    public void dfs(String clientAddress, String shopAddress) {             // обход в глубину
        NodeTree vertexClient = vertexList.getNodeByValue(clientAddress);   //  вершина клиента по адресу
        NodeTree vertexShop = vertexList.getNodeByValue(shopAddress);       //  вершина магазина по адресу
        /*
        if  vertexClient and vertexShop != null  добавить проверку.
         */
        int clientId = vertexClient.id;
        int shopId = vertexShop.id;

        vertexClient.isVisited = true;
        displayVertex(clientId);
        stack.push(clientId);

        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex((Integer) stack.peek());          // вынуть индекс смежной веришины, еcли есть 1, нет -1
            if (v == -1) {                                                  // если не пройденных смежных вершин нет
                stack.pop();                                                // элемент извлекается из стека
            }
            else if (v == shopId) {                                      // STOP BFS if you find path Client -> Shop
                displayVertex(v);
                break;
            }
            else {
                NodeTree vertex = vertexList.getNodeById(v);
                vertex.isVisited = true;
                displayVertex(v);
                stack.push(v);                                              // элемент попадает на вершину стека
            }
        }
        stack.clear();
        resetVisitedVertex();
    }           // client -> shop

    public void bfs(String clientAddress, String shopAddress) {               // client -> shop
         /*
        if  vertexClient and vertexShop != null  добавить проверку.
         */
        NodeTree vertexClient = vertexList.getNodeByValue(clientAddress);
        NodeTree vertexShop = vertexList.getNodeByValue(shopAddress);
        vertexClient.isVisited = true;                                      // берётся вершина клиента
        int clientId = vertexClient.id;
        int shopId = vertexShop.id;

        displayVertex(clientId);
        queue.add(clientId);
        int unvisitedId = -1;

        while (!queue.isEmpty()) {
            if (shopId == unvisitedId)       break;
            int vertexId = (Integer) queue.poll();

            while ((unvisitedId = getUnvisitedVertex(vertexId)) != -1) {
                NodeTree vertex = vertexList.getNodeById(unvisitedId);
                vertex.isVisited = true;
                displayVertex(unvisitedId);
                if (unvisitedId == shopId)  break;
                queue.add(unvisitedId);
            }
        }
        resetVisitedVertex();
        queue.clear();

    }

    public void dijkstra(String clientAddress, String shopAddress) {            // shop -> client
        NodeTree vertexClient = vertexList.getNodeByValue(clientAddress);       // вершина клієнта за адресою
        NodeTree vertexShop = vertexList.getNodeByValue(shopAddress);           // вершина магазина за адресою
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
        System.out.printf("Min distance from Id: %d Shop: %s\tto\tId: %d Client: %s ----> %d minute\n",
                shopId, vertexShop.key, clientId, vertexClient.key, distance[clientId]);
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
            Node head = vertexList.getHead();
            NodeTree vertex = null;
            while (head != null) {
                vertex = head.vertex;
                if (vertex.id == j)
                    break;
                head = head.next;
            }
            if (adjMatrix[v][j] != 0 && (vertex != null && !vertex.isVisited) ) {
                return j; //возвращает первую найденную вершину
            }
        }
        return -1;
    }

    public void resetVisitedVertex() {                       // сброс флагов посещенных вершин
        Node head = vertexList.getHead();
        while (head != null) {
            head.vertex.isVisited = false;
            head = head.next;
        }
    }

    public void removeVertex(int vertexId) {
        /*
        перевірка vertexList.getNodeById(vertexId) != null
         */
        vertexList.remove(vertexList.getNodeById(vertexId));                         // видаляє вершину зі списка вершин
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix[0].length; j++)
                if ((i == vertexId || j == vertexId) && adjMatrix[i][j] != 0)       // якщо з вершини чи до вершини є шлях, скидаємо в 0.
                    adjMatrix[i][j] = 0;
    }



//    public static class Vertex {            // адреса
//        public String label;
//        public int id;
//        public boolean isVisited;
//
//        public Vertex(String label, int id) {
//            this.label = label;
//            this.id = id;
//            isVisited = false;
//        }
//    }

}

