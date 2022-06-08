package Course_work;

import MyLibrary.QueueList;
import org.jetbrains.annotations.TestOnly;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();
        QueueList deliveryQueue = new QueueList();      // Черга клієнтів
        Graph graph = new Graph();
        InputData inputData = new InputData();          // зчитування даних з файлів

        inputData.readFiles(shopMap, clientMap, deliveryQueue);
        shopMap.print();
        System.out.println();
        clientMap.print();
        System.out.println();
        deliveryQueue.printQueue();


        /* test get value by key */

        System.out.println(shopMap.getValue("Atb"));
        System.out.println(shopMap.getValueRecursive("Comfy"));
        System.out.println();
        System.out.println(clientMap.getValue("Abram #5"));
        System.out.println(clientMap.getValueRecursive("Max #3"));

    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}
