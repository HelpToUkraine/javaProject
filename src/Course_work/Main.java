package Course_work;

import MyLibrary.QueueList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();
        QueueList deliveryQueue = new QueueList();                          // Черга клієнтів
        InputData inputData = new InputData();                              // зчитування даних з файлів

        /* create graph after read data in Files */
        Graph graph = inputData.readFiles(shopMap, clientMap, deliveryQueue);
        Scanner input = new Scanner(System.in);

        /*
        Output info Tree, Graph, Queue
         */

        System.out.println("Shop -> address\n");
        shopMap.print();

        System.out.println("\nClient -> address\n");
        clientMap.print();

        System.out.println("\nQueue client's to delivery\n");
        deliveryQueue.printQueue();

//        System.out.println("Adjacency Matrix");
//        graph.printMatrix();



        /* заповнення списка вершин графа */
        clientMap.traverseInOrder(graph);
        shopMap.traverseInOrder(graph);

        System.out.println("Вершини графа: ");
        graph.vertexList.print();

        System.out.println("Інформація про вершину: ");
        graph.displayVertex(2);
        graph.displayVertex(0);
        graph.displayVertex(6);
        graph.displayVertex(18);
        graph.displayVertex(-1);



         /* Зчитує чергу замволень клієнтів. Після кожної ітерації видаляється клієнт з замовлення */
        while (true) {
            System.out.print("\nОбробити доставку наступного клієнта <1> | Закрити програму <Any Key>: ");
            String var = input.nextLine();
            switch (var) {
                case "1" -> nextDelivery(graph, shopMap, clientMap, deliveryQueue);
                case "2" -> System.out.println("Kirgo Lox");
                case "3" -> System.out.println("scam lox");
                case "4" -> System.out.println("cho3kopai lox");
                default -> {
                    System.err.println("The program completed successfully");
                    System.exit(0);
                }
            }
        }

        /*
        test get value by key
        */

//        System.out.println("\n\nTest: find value by key:");
//        System.out.println("Key: Atb -> : " + shopMap.getValue("Atb"));
//        System.out.println("Key: Comfy -> " + shopMap.getValueRecursive("Comfy"));
//        System.out.println("Key: Abram -> " + clientMap.getValue("Abram"));
//        System.out.println("Key: Max -> " + clientMap.getValueRecursive("Max"));
    }

    public static void nextDelivery(Graph graph, BinaryTree shopMap, BinaryTree clientMap, QueueList deliveryQueue) {
        if (deliveryQueue.getHead() != null) {
            QueueList.Node head = deliveryQueue.remove();
            String clientAddress = clientMap.getValue((String) head.key);
            String shopAddress = shopMap.getValue((String) head.value);
            graph.bfs(shopAddress, clientAddress);
            graph.dfs(shopAddress, clientAddress);
            graph.dijkstra(shopAddress, clientAddress);
            System.out.printf("Client '%s'\taddress: %s\nShop '%s'\taddress: %s\n",  head.key, clientAddress, head.value, shopAddress);
        } else
            System.out.println("All orders are delivered");
    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}
