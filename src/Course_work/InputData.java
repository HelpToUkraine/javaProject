package Course_work;

import MyLibrary.QueueList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputData {
    File shop = new File("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Shops.txt");
    File client = new File("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Clients.txt");
    File delivery = new File("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Delivery.txt");
    File adjMatrix = new File("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Graph.txt");

    public Graph readFiles(BinaryTree shopMap, BinaryTree clientMap, QueueList deliveryQueue) throws FileNotFoundException {
        int id = 0;      /* id Vertex client and shop */

        /* Read Shops.txt */
        Scanner scanner1 = new Scanner(shop);
        while (scanner1.hasNextLine()) {
            String[] array = scanner1.nextLine().split(" -> ");
            shopMap.put(array[0], array[1], id++);
        }

        /* Read Clients.txt */
        Scanner scanner2 = new Scanner(client);
        while (scanner2.hasNextLine()) {
            String[] array = scanner2.nextLine().split(" -> ");
            clientMap.put(array[0], array[1], id++);
        }

        /* Read Delivery.txt  and write to - > Queue delivery */
        Scanner scanner3 = new Scanner(delivery);
        while (scanner3.hasNextLine()) {
            String[] array = scanner3.nextLine().split(" -> ");
            deliveryQueue.add(array[0], array[1]);
        }

        /* Read Graph.txt and create Graph */
        Scanner scanner4 = new Scanner(adjMatrix);
        Graph graph = new Graph(id);
        while (scanner4.hasNextLine()) {
            String[] strings = scanner4.nextLine().split(" -> ");
            int[] numbers = new int[strings.length];
            for (int i = 0; i < numbers.length; i++)
                numbers[i] = Integer.parseInt(strings[i]);
            graph.addEdge(numbers[0], numbers[1], numbers[2]);   // start -> end -> weight
        }
        return graph;
    }

}