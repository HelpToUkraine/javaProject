package Lab2_PSA;

import java.util.Arrays;
import java.util.Random;

public class PZ_4 extends Lab1{
    public static void main(String[] args) {
        int size1 = 15;
        int[] array = new int[size1];
        Random random = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(50)-25;

        System.out.println("Task1:\nЗнайти min element та замінити 0, визначити середнє арифметичне\n" + Arrays.toString(array));
        String result1 = task1(array);
        System.out.println(Arrays.toString(array));
        System.out.println(result1);

        int size2 = 6;
        int[][] arr = initialize(size2);
        int result2 = task2(arr);
        System.out.println("\nTask2:\nЗнайти суму елементів вище головної діагоналі, включаючи її");
        print_array(arr);
        System.out.println("Sum of elements: " + result2);
    }

    static String task1(int[] array) {
        double sum = 0;
        int min = array[0], index = 0;
        for (int i = 1; i < array.length; i++)
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        array[index] = 0;
        for (int i : array)
            sum += i;
        return "Min element: " + min + "\nAverage sum: " + (sum / array.length);
    }

   static int task2(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
           for (int j = 0; j < array.length - i; j++)
               sum += array[i][j];
       return  sum;
    }

}
