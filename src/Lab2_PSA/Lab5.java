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
        System.out.print("Введіть ключ для пошуку в списку: ");
        try {
            key = input.nextInt();
        }catch (Exception e){
            System.out.println("Error input");
            System.exit(0);
        }
        list.add(4);
        list.add(1);
        list.add(-6);
        list.add(30);
        list.add(5);
        list.add(7);
        list.add(1);
        list.add(30);
        list.add(-3);
        list.add(-20);
        list.print();

        task1(list, stack, key);
        System.out.println("Після видалення ключа та обміну попереднього та наступніх елементів");
        list.print();
        System.out.println("Стек з елементів LinkedList: ");
        stack.printStack();
        System.out.printf("Середнє арифметичне елементів у Stack: %.2f", individual_task(stack));

        System.out.println("\n\nDoublyLinkedList");
        doubly_list.add(10);
        doubly_list.add(15);
        doubly_list.add(-1);
        doubly_list.add(-6);
        doubly_list.add(-11);
        doubly_list.add(30);
        doubly_list.add(37);
        doubly_list.add(30);
        doubly_list.add(4);
        doubly_list.print();
        task2(doubly_list, stack, key);
        System.out.println("\nПісля видалення ключа: " + key);
        doubly_list.print();
        System.out.println("\nСтек з елементів DoublyLinkedList");
        stack.printStack();
        System.out.printf("Середнє арифметичне елементів у Stack: %.2f", individual_task(stack));
    }

    public static void task1(LinkedList list, Stack stack, int key) {
        Node currentNode = list.getHead();
        if (list.contains(key)) {
            Node deleteNode = list.getNode(key);
            if (currentNode.next == deleteNode) {
                while (currentNode.next != null)
                    currentNode = currentNode.next;
                currentNode.next = list.getHead();
                currentNode.next.next = null;
                list.setHead(deleteNode.next);
            }
            else if (deleteNode != currentNode && deleteNode.next != null) { // node not first and not last
                Node previousNode = null;
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
            list.remove(key);
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
            Object temp = stack.pop();
            if (temp instanceof Integer)
                average += (int) temp;
        }
        return  average / size;
    }

}
