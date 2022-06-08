package Course_work;

import MyLibrary.QueueList;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();
        QueueList deliveryQueue = new QueueList();                          // Черга клієнтів
        InputData inputData = new InputData();                              // зчитування даних з файлів
        Graph graph = inputData.readFiles(shopMap, clientMap, deliveryQueue);

        System.out.println("Shop -> address\n");
        shopMap.print();

        System.out.println("\nClient -> address\n");
        clientMap.print();

        System.out.println("\nQueue client's to delivery\n");
        deliveryQueue.printQueue();

        System.out.println("Adjacency Matrix");
        graph.printMatrix();

        while (deliveryQueue.getHead() != null) {
            QueueList.Node head = deliveryQueue.remove();
            System.out.print(head.key + " -> " + head.value + "\t");
        }

        /* test get value by key */
        System.out.println("\nTest: find value by key:");
        System.out.println("Key: Atb -> : " + shopMap.getValue("Atb"));
        System.out.println("Key: Comfy -> " + shopMap.getValueRecursive("Comfy"));
        System.out.println("Key: Abram -> " + clientMap.getValue("Abram"));
        System.out.println("Key: Max -> " + clientMap.getValueRecursive("Max"));
    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}
