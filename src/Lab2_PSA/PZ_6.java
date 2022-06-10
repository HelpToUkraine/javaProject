package Lab2_PSA;

import MyLibrary.QueueList;
import MyLibrary.QueueArray;
import java.util.Scanner;

public class PZ_6 {
    public static void main(String[] args) {
        QueueList queueList = new QueueList();
        QueueArray queueArray = new QueueArray(10);
        Scanner input = new Scanner(System.in);
        int index = 0;

        System.out.print("Введіть індекс видалення елемента з черги: ");
        try {
            index = input.nextInt();
        }catch (Exception E) {
            System.err.println("Error: input");
            System.exit(0);
        }
        for (int i = 1; i <= 5; i++)
            queueList.add(i);

        System.out.println("Queue List:");
        queueList.print();
        System.out.println("Після видалення елемента з індексом: " + index);
        queueList.removeIndexOf(index);
        queueList.print();
        System.out.println("Елемент в Queue List з індексом: " + index + " -> " + queueList.get(index));

        for (int i = 1; i <= 5; i++)
            queueArray.add(i*2);

        System.out.println("\nQueue Array(кільцева):");
        queueArray.printQueue();

        System.out.println("Після видалення елемента з індексом: " + index);
        queueArray.removeIndexOf(index);
        queueArray.printQueue();
        System.out.println("Елемент в Queue Array з індексом: " + index + " -> " + queueArray.get(index));
    }

}
