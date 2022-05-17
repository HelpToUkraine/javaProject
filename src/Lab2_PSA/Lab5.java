/* 1. Створити лінійний однозв’язний список, вивести його. Якщо в списку є елемент із заданим ключем, вилучити його, а попередній та настуні
поміняти місцями. Виконати завдання згідно варіанту.
2. Створити двозв’язний список, вивести його. Якщо в списку є елемент із заданим ключем, вилучити його. Виконати завдання
згідно варіанту з двозвязним */

package Lab2_PSA;

import MyLibrary.*;
import MyLibrary.LinkedList.Node;

import java.util.Scanner;

public class Lab5 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        DoubleLinkedList doubly_list = new DoubleLinkedList();
        Stack stack = new Stack(10);

        int key = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Input key for founding in list: ");
        try {
            key = input.nextInt();
        }catch (Exception e){
            System.out.println("Error input");
            System.exit(0);
        }
        System.out.println("\nSingle LinkedList: ");
        list.add(4);
        list.add(1);
        list.add(-6);
        list.add(30);
        list.add(5);
        list.add(7);
        list.add(1);
        list.add(-3);
        list.add(-20);
        list.print();

        task1(list, stack, key);
        System.out.println("After delete key in list and reverse previous and next elements:");
        list.print();
        System.out.println("Stack of elements SingleList: ");
        double average = individual_task(stack);
        System.out.printf("\nAverage sum elements in Stack: %.2f", average);

        System.out.println("\n\nDoubly LinkedList");
        doubly_list.add(10);
        doubly_list.add(15);
        doubly_list.add(-6);
        doubly_list.add(-11);
        doubly_list.add(30);
        doubly_list.add(37);
        doubly_list.add(30);
        doubly_list.add(4);
        doubly_list.add(-1);
        doubly_list.print();
        doubly_list.remove(-11);
        task2(doubly_list, stack, key);
        System.out.println("\nAfter delete key in list:");
        doubly_list.print();
        System.out.println("\nStack of the elements DoublyList");
        double average2 = individual_task(stack);
        System.out.printf("\nAverage sum of elements in Stack: %.2f", average2);

    }

    public static void task1(LinkedList list, Stack stack, int key) {
        Node currentNode = list.getHead();
        if (list.contains(key)) {
            Node deleteNode = list.getNode(key);
            Node previousNode = null;
            //list.remove(key);
            while (currentNode.next.next != deleteNode) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            previousNode = currentNode.next;
            currentNode.next = deleteNode.next;  // сохранили линк после удаления ноди
            while (currentNode.next != null)
                currentNode = currentNode.next;
            currentNode.next = previousNode;  // попередню в кінець заміна
            currentNode.next.next = null;
        }
        Node firstNode = list.getHead();
        while (firstNode != null) {
            stack.push(firstNode.data);
            firstNode = firstNode.next;
        }
    }

    public static void task2(DoubleLinkedList list, Stack stack, int key) {
        DoubleLinkedList.Node currentNode = list.getHead();
        if (list.contains(key))
            list.remove(key);
        while (currentNode != null) {
            stack.push(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public static double individual_task(Stack stack) {
        double average = 0;
        int size = stack.getSize();
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            average += temp;
            System.out.print(temp + " ");
        }
        return  average / size;
    }

}
