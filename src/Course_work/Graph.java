

package Course_work;

import Course_work.LinkedList.Node;
import Course_work.BinaryTree.NodeTree;
import MyLibrary.Stack;
import MyLibrary.QueueList;

public class Graph {
    public int[][] adjMatrix;       // матрица смежности
    public int currentVertex;       // текущее количество вершин
    LinkedList vertexList;          // массив вершин
    Stack stack = new Stack(100);
    QueueList queueList;

    public Graph(int maxVertex) {
        vertexList = new LinkedList();
        adjMatrix = new int[maxVertex][maxVertex];
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
        NodeTree vertexClient = vertexList.getNodeByValue(clientAddress);
        NodeTree vertexShop = vertexList.getNodeByValue(shopAddress);
        vertexClient.isVisited = true;                                      // берётся вершина клиента
        int clientId = vertexClient.id;
        int shopId = vertexShop.id;

        displayVertex(clientId);
        stack.push(clientId);

        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex((Integer) stack.peek());          // вынуть индекс смежной веришины, еcли есть 1, нет -1
            if (v == -1) {                                                  // если не пройденных смежных вершин нет
                stack.pop();                                                // элемент извлекается из стека
            }
            else if (v == shopId) {                         // STOP BFS if you find shopID
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
        resetVisitedVertex();
    }

    public void resetVisitedVertex() {
        Node head = vertexList.getHead();                               // сброс флагов
        while (head != null) {
            head.vertex.isVisited = false;
            head = head.next;
        }
    }

    private int getUnvisitedVertex(int v) {
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


    public void bfs(String clientAddress, String shopAddress) {

    }

    public void dijkstra(String clientAddress, String shopAddress) {

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

