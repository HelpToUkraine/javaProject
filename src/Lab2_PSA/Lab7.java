package Lab2_PSA;

import MyLibrary.BinarySearchTree;
import MyLibrary.BinarySearchTree.Node;

public class Lab7 {
    static int counter = 0;
    static int pairedSubTree = 0;

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(20);
        tree.put(30);
        tree.put(10);
        tree.put(14);
        tree.put(6);
        tree.put(16);
        tree.put(13);
        tree.put(3);
        tree.put(8);
        tree.put(34);
        tree.put(33);
        tree.put(36);
        tree.put(26);
        tree.put(23);
        tree.put(28);

        System.out.println("TraversePostOrder: left subTree -> right subTree -> root");
        tree.print();

        task1(tree);
        System.out.println("\n\nTask1: 'Кількість елементів дерева, що закінчуються на цифру 3': " + counter);

        System.out.println("\nTask2: 'Вивести перелік вузлів, у яких кількість парних елементів в правому і лівому піддереві рівні'");
        task2(tree);
    }

    public static void task1(BinarySearchTree<Integer> tree) {
        task1(tree.getRoot());
    }

    public static void task1(Node<Integer> current) {
        if (current != null) {
            task1(current.leftChild);
            task1(current.rightChild);
            if (current.value % 10 == 3)    counter++;
        }
    }

    public static void task2(BinarySearchTree<Integer> tree) {
        task2(tree.getRoot());
    }

    public static void task2(Node<Integer> current) {
        if (current != null) {
            task2(current.leftChild);
            task2(current.rightChild);
            pairedSubTree = 0;
            traversePostOrder(current.leftChild, current);
            int temp = pairedSubTree; pairedSubTree = 0;
            traversePostOrder(current.rightChild, current);
            if (temp == pairedSubTree && pairedSubTree != 0) {
                System.out.printf("Вузол: %d\tПарні елементи у leftSubTree: %d\t\tПарні елементи у rightSubTree: %d\n",
                        current.value, temp, pairedSubTree);
            }
        }
    }

    public static void traversePostOrder(Node<Integer> current, Node<Integer> root) {
        if (current != null) {
            traversePostOrder(current.leftChild, root);
            traversePostOrder(current.rightChild, root);
            if (current != root && current.value % 2 == 0) pairedSubTree++;
        }
    }

}
