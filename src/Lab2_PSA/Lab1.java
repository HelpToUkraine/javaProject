package Lab2_PSA;

import java.util.Random;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        int task = 0, var = 0, sdvig = 5, size = 0; long finish = 0;
        System.out.print("\nКакое задание выполнить (1-2): ");
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt())
            task = input.nextInt();

        if (task != 1 && task != 2) {
            System.out.println("Error: Такого задания нету");
            System.exit(0);
        }
        System.out.print("Выберите размер массива (1-4) | задайте с клавиатуры: ");
        System.out.println("\n1. 10x10\n2. 50x50\n3. 100x100\n4. 500x500");
        if (input.hasNextInt())
            var = input.nextInt();
        else {
            System.out.println("Error: Неверно введен размер массива");
            System.exit(0);
        }
        input.close();
        if (var == 1) size = 10;
        else if (var == 2) size = 50;
        else if (var == 3) size = 100;
        else if (var == 4) size = 500;
        else    size = var;  

        System.out.printf("\nГенерация матрицы %dх%d для задания %d...\n", size, size, task);
        int[][] array = initialize(size);           // ініціалізація масиву
        print_array(array);
        long start = System.nanoTime();
        switch (task) {
            case 1:        
                int[] res = task1(array);
                finish = System.nanoTime();
                System.out.printf("\nМаксимальний елемент масиву: %d | Стовбець: №%d | Iтерацiй: %d",res[0],res[1],res[2]);
                System.out.printf("\n\nДодатнi елементи [масиву]: %d\t-->\tCтовбця №%d: %d\nЛiворуч: %d\nПраворуч: %d",res[3],res[1],res[4],res[5],res[6]);
                System.out.printf("\n\nВiд'ємнi елементи [масиву]: %d\t-->\tCтовбця №%d: %d\nЛiворуч: %d\nПраворуч: %d",res[7],res[1],res[8],res[9],res[10]);  
                break;

            case 2:
                int[][] array_sdvig = task2(array, sdvig);
                finish = System.nanoTime();
                System.out.printf("\nМатрица со сдвигом в право: %d\n", sdvig);
                print_array(array_sdvig);
                break;
        }
        long time = finish - start;
        System.out.printf("\n\nВремя работы: %d н/с", time);
    }

    public static void print_array(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++)
                System.out.printf("%5d", array[i][j]);
            System.out.println();
        }     
    }

    public static int[][] initialize(int size) {            // заповнення рандомними значеннями
        Random random = new Random();
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++)          
            for (int j = 0; j < array[0].length; j++) 
                array[i][j] = random.nextInt(2);
        return array;   
    }

    public static int[] task1(int[][] array) {
        int left_plus = 0, left_minus = 0, right_plus = 0, right_minus = 0, column_plus = 0, column_minus = 0;
        int max_element = array[0][0], column = 0, count = 0, sum_plus = 0, sum_minus = 0;          
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                count += 1;
                if (array[i][j] > 0) {                  // підрахунок додатніх в лівій та правій частині
                    if (j < array[0].length / 2)
                        left_plus += 1;
                    else if (j > (array[0].length-1) / 2)
                        right_plus += 1;
                }
                else if (array[i][j] < 0) {             // підрахунок від'ємних в лівій та правій частині
                    if (j < array[0].length / 2)
                        left_minus += 1;
                    else if (j > (array[0].length-1) / 2)
                        right_minus += 1;
                }              
                if (array[i][j] > max_element) {        // пошук макс. ел. та №стовпця
                    column = j;                         // стовбець
                    max_element = array[i][j];
                }
            }
        }
        for (int j = 0; j < array.length; j++) {        // додатні та від'ємні значення в стовбці
            count += 1;         
            if (array[j][column] > 0) 
                column_plus += 1;
            else if  (array[j][column] < 0)
                column_minus += 1;
        }
        column += 1;  sum_plus = left_plus + right_plus; sum_minus = left_minus + right_minus;
        return new int[] {max_element,column,count,sum_plus,column_plus,left_plus,right_plus,sum_minus,column_minus,left_minus,right_minus};
    }

    public static int[][] task2(int[][] array, int number) {
        int[][] array_sdvig = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int sdvig = j + number;              // сдвиг на n елементів 
                if (sdvig >= array.length)
                    sdvig %= array.length;
                array_sdvig[i][sdvig] = array[i][j];
            }
        }
        return array_sdvig;
    } 
}