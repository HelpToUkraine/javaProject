package Lab2_PSA;

import java.util.*;

public class Lab4 {
    public static void main(String[] args) {
        int size = 0;
        int key = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Input size of array: ");
        try {
            size = Math.abs(input.nextInt());
        }catch (Exception e){
            System.out.println("Error: Input size of array");
            System.exit(0);
        }
        System.out.print("Input key to found in array: ");
        try {
            key = input.nextInt();
        }catch (Exception e){
            System.out.println("Error: Input key");
            System.exit(0);
        }
        System.out.print("BinarySearch <1> | BarrierSearch <2>: ");
        String var = input.next();

        int[][] array = new int[size][size];
        ArrayList<Integer> result = new ArrayList<>();
        long time = System.nanoTime();
        switch (var) {
            case ("1") -> {
                array = initialize(size);
                for (int i = 0; i < size; i++)
                    if (array[i][0] <= key && key <= array[i][size - 1]) {
                        result.add(i + 1);                        //рядок
                        result.add(binarySearch(array[i], key)); //стовбець
                    }
            }
            case ("2") -> {
                array = randomInitialize(size);
                for (int i = 0; i < size; i++) {
                    int temp = barrierSearch(array[i], key);
                    if (temp != -1) {
                        result.add(i + 1);
                        result.add(temp);
                    }
                }
            }
            default -> {
                System.out.println("Error: Input var");
                System.exit(0);
            }
        }
        time = System.nanoTime() - time;
        arrayPrint(array);
        if (result.isEmpty())
            System.out.println("Key don't is in array");
        else
            for (int i = 0; i < result.size(); i+=2)
                if (result.get(i+1) != -1)
                    System.out.printf("\nKey found in position --> row %d, column %d", result.get(i), result.get(i+1));
        System.out.println("\nTime finding element n/s: " + time);
    }

    public static int binarySearch(int[] array, int key) {
        int start = 0, end = array.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == key)       return mid+1;
            else if (array[mid] < key)   start = mid + 1;
            else                         end = mid - 1;
        }
        return -1;
    }

    public static int barrierSearch(int[] array, int key) {
        int N = array.length, temp = array[N-1], i = 0;
        array[N-1] = key;
        while (array[i] != key)     i++;
        array[N-1] = temp;
        if (i == N-1 && temp != key)   return -1;
        else return i+1;
    }

    public static int[][] initialize(int size) {
        int[][] array = new int[size][size];
        int count = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                array[i][j] = count++;
        return array;
    }

    public static int[][] randomInitialize(int size) {
        Random random = new Random();
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                array[i][j] = random.nextInt(100);
        return array;
    }

    public static void arrayPrint(int[][] array) {
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++)
                System.out.printf("%5d", ints[j]);
            System.out.println();
        }
    }
}
