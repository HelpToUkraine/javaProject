package Course_work;

import MyLibrary.QueueList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();
        QueueList deliveryQueue = new QueueList();                          // Черга клієнтів
        InputData inputData = new InputData();                              // зчитування даних з файлів

        // create graph after read data in Files
        Graph graph = inputData.readFiles(shopMap, clientMap, deliveryQueue);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\nОбробити доставку наступного клієнта <1> | Закрити програму <Any Key>: ");
            String var = input.nextLine();
            switch (var) {
                case "1" -> nextDelivery(shopMap, clientMap, deliveryQueue);
                case "2" -> System.out.println("Kirgo Lox");
//                case "3" -> nextDelivery(shopMap, clientMap, deliveryQueue);
//                case "4" -> System.out.println("Kirgo Lox");
                default -> {
                    System.err.println("Error input");
                    System.exit(0);
                }
            }
        }


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

        /*
        Зчитує чергу замволень клієнтів. Після кожної ітерації видаляється клієнт з замовлення
         */

        /* test get value by key */
//        System.out.println("\n\nTest: find value by key:");
//        System.out.println("Key: Atb -> : " + shopMap.getValue("Atb"));
//        System.out.println("Key: Comfy -> " + shopMap.getValueRecursive("Comfy"));
//        System.out.println("Key: Abram -> " + clientMap.getValue("Abram"));
//        System.out.println("Key: Max -> " + clientMap.getValueRecursive("Max"));
    }

    public static void nextDelivery(BinaryTree shopMap, BinaryTree clientMap, QueueList deliveryQueue) {
        if (deliveryQueue.getHead() != null) {
            QueueList.Node head = deliveryQueue.remove();
            String clientAddress = clientMap.getValue((String) head.key);
            String shopAddress = shopMap.getValue((String) head.value);
            System.out.printf("Client address: %s \tShopAddress: %s\n",  clientAddress, shopAddress);
        } else
            System.out.println("All order delivery");
    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}
