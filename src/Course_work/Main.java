package Course_work;

import MyLibrary.LinkedList;
import MyLibrary.Stack;
import MyLibrary.QueueList;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();

        readData("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Shops.txt", shopMap);
        shopMap.print();

        System.out.println(shopMap.getValue("Atb"));
        System.out.println(shopMap.getValueRecursive("Comfy"));

        System.out.println();
        readData("D:\\Code\\javaProject\\src\\Course_work\\Config files\\Clients.txt", clientMap);
        clientMap.print();
        System.out.println(clientMap.getValue("client1"));
        System.out.println(clientMap.getValueRecursive("client4"));

    }

    public static  void readData(String path, BinaryTree tree) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] array = line.split(" -> ");
            tree.put(array[0], array[1]);
        }
        scanner.close();

    }
}
