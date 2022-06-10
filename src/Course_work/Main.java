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

//        System.out.println("Shop -> address\n");
//        shopMap.print();
//
//        System.out.println("\nClient -> address\n");
//        clientMap.print();
//
//        System.out.println("\nQueue client's to delivery\n");
//        deliveryQueue.printQueue();
//
//        System.out.println("Adjacency Matrix");
//        graph.printMatrix();


        /* заповнення списка вершин графа з Binary Tree */
        clientMap.traverseInOrder(graph);
        shopMap.traverseInOrder(graph);

//
//        System.out.println("Вершини графа: ");
//        graph.vertexList.print();


//        System.out.println("Інформація про вершину: ");
//        graph.displayVertex(13);
//        graph.displayVertex(1);

        /* Зчитує чергу замволень клієнтів. Після кожної ітерації видаляється клієнт з замовлення */
        while (true) {
            System.out.print("""   
                             
                    Обробити доставку наступного клієнта: <1>
                    Черга замовлень: <2>
                    Вершини графа: <3>
                    Таблиця магазинів <4>
                    Таблиця клієнтів <5>
                    Матриця суміжності: <6>
                    Розірвати вершину графа: <7>
                    Закрити програму: <Another Key>
                    """);
            String var = input.nextLine();
            switch (var) {
                case "1" -> nextDelivery(graph, shopMap, clientMap, deliveryQueue);
                case "2" -> deliveryQueue.print();
                case "3" -> graph.vertexList.print();
                case "4" -> shopMap.print();
                case "5" -> clientMap.print();
                case "6" -> graph.printMatrix();
                case "7" -> {
                    System.out.print("Введіть номер(id) вершини для видалення: ");
                    String delVertex = input.nextLine();
                    graph.removeVertex(Integer.parseInt(delVertex));
                }
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
        if (deliveryQueue.getHead() != null) {                                   // "Max -> Apple"
            QueueList.Node order = deliveryQueue.remove();                       // дістається клієнт і видаляється
            String clientAddress = clientMap.getValue((String) order.key);
            String shopAddress = shopMap.getValue((String) order.value);

//            System.out.printf("DFS:\nClient: %s -> Shop: %s\nClientAddress: %s -> ShopAddress: %s\n\n",
//                    head.key, head.value, clientAddress, shopAddress);

            System.out.printf("\nClient: '%s'\taddress: %s\nShop: '%s'\taddress: %s\n",
                    order.key, clientAddress, order.value, shopAddress);

            System.out.println("\nDFS: --------------------------------");
            graph.dfs(clientAddress, shopAddress);

            System.out.println("\nBFS: --------------------------------");
            graph.bfs(clientAddress, shopAddress);

            System.out.println("\nDijkstra: --------------------------------");
            graph.dijkstra(clientAddress, shopAddress);

            /*
            Спросить хотите ли разорвать вершину и пересчитать пути?
            если да -> разорвать и перезапустить.
             */

        } else
            System.out.println("All orders are delivered");
    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}
