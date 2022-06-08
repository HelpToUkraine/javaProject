package Course_work;

public class Graph {
    public int[][] adjMatrix;
    public int currentVertex;
    LinkedList<Vertex> vertexList;

    public Graph(int maxVertex) {
        vertexList = new LinkedList<>();
        adjMatrix = new int[maxVertex][maxVertex];
        currentVertex = 0;

    }

    public void addEdge(int start, int end, int value) {
        adjMatrix[start][end] = value;

    }

    public void addVertex(String name) {
        vertexList.add(new Vertex(name));
    }

    public void printMatrix() {
        for (int[] ints : adjMatrix) {
            for (int number : ints)
                System.out.print(number + "\t");
            System.out.println();
        }
    }


    public static class Vertex {
        public String name;
        public boolean isVisited;

        public Vertex(String name) {
            this.name = name;
            isVisited = false;
        }
    }

    public static class Edge {

    }
}

