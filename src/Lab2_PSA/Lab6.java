package Lab2_PSA;

import MyLibrary.LinkedList;
import MyLibrary.LinkedList.*;
import java.util.Scanner;

public class Lab6 {
    public static void main(String[] args) throws Exception{
        LinkedList value = new LinkedList();    // save value
        LinkedList row = new LinkedList();      // save index row with value
        LinkedList column = new LinkedList();   // save index column with value
        Scanner input = new Scanner(System.in);
        int size = 0;

        System.out.print("Введіть розмір матриці n*n: ");
        try {
            size = input.nextInt();
        } catch (Exception e) {
            System.err.print("Error: input size matrix");
            System.exit(0);
        }
        int[][] sparse_matrix = sparseCreate(size, value, row, column);
        System.out.println("Розріджена матриця");
        arrayPrint(sparse_matrix);

        System.out.println("\nСтиснена матриця");
        value.print();
        row.print();
        column.print();
        System.out.println("\nСума елементів в стовбчику: ");
        task17(size, value, column);
    }

    public static int[][] sparseCreate(int size, LinkedList value, LinkedList row, LinkedList column) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((int) (Math.random()*(size)) % 5 == 0) {
                    int number = (int) (Math.random()*size-1)+1;
                    array[i][j] = number;
                    value.add(number);
                    row.add(i);
                    column.add(j);
                }
                else array[i][j] = 0;
            }
        }
        return array;
    }

    public static void arrayPrint(int[][] array) {
        for (int[] ints : array){
            for (int number : ints)
                System.out.print(number + "\t");
            System.out.println();
        }
    }

    public static void task17(int size, LinkedList value, LinkedList column) {
        for (int j = 0; j < size; j++) {            // index of column
            Node currentColumn = column.getHead();
            Node currentValue = value.getHead();
            int sum = 0;
            while (currentColumn != null) {
                if (currentColumn.data.equals(j))
                    sum += (int) currentValue.data;
                currentValue = currentValue.next;
                currentColumn = currentColumn.next;
            }
            System.out.printf("№%d = %d\n", j, sum);
        }
    }

}

