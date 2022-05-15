package Lab2_PSA;

import java.util.Scanner;

public class Lab3 extends Lab1{
    public static void main(String[] args) {
        int size = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Input size of array: ");
        try {
            size = input.nextInt();
        } catch (Exception e){
            System.out.println("Error input size");
            System.exit(0);
        }
        int[][] array = initialize(size); int N = array.length;
        print_array(array);
        long time = System.nanoTime();
        for (int i = 0; i < N; i++) {
            int[] column = new int[N-i];
            for (int j = 0; j < N-i; j++)
                column[j] = array[j][i];
            insertionSort(column);
            for (int k = 0; k < column.length; k++)
                array[k][i] = column[column.length-k-1];
        }
        time = (System.nanoTime() - time);
        System.out.println("Sorted with Insertion sort:");
        print_array(array);
        System.out.printf("Sorting time: %d (n/s)", time);
    }
    public static void insertionSort(int[] arr) { // вставка
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > key; j--)
                arr[j + 1] = arr[j];
            arr[j + 1] = key;
        }
    }
    public static void selectionSort(int[] arr) { // вибір
        for (int i = 0; i < arr.length-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < arr.length; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++)
            for (int j = 0; j < arr.length-i-1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
}
