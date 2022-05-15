package Lab2_PSA;

import java.util.Random;

public class Task_Lab1 {

    public void print_array(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++)
                System.out.printf("%7d", array[i][j]);
            System.out.println();
        }     
    }

    public int[][] initialize(int size) {            
        Random random = new Random();
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++)          
            for (int j = 0; j < array[0].length; j++) 
                array[i][j] = random.nextInt(200)-100;
        return array;   
    }

    public int[] task1(int[][] array) {
        int left_plus = 0, left_minus = 0, right_plus = 0, right_minus = 0, column_plus = 0, column_minus = 0;
        int max_element = array[0][0], column = 0, count = 0, sum_plus = 0, sum_minus = 0;          
        count += 6;
        for (int i = 0; i < array.length; i++) {
            count += 7;
            for (int j = 0; j < array[0].length; j++) { //                                                   (7)
                if (array[i][j] > 0) {                  // підрахунок додатніх в лівій та правій частині    (14)
                    if (j < array[0].length / 2)
                        left_plus += 1;
                    else if (j > (array[0].length-1) / 2)
                        right_plus += 1;
                }
                else if (array[i][j] < 0) {             // підрахунок від'ємних в лівій та правій частині   (14)
                    if (j < array[0].length / 2)
                        left_minus += 1;
                    else if (j > (array[0].length-1) / 2)
                        right_minus += 1;
                }
                if (array[i][j] > max_element) {        // пошук макс. ел. та №стовпця
                    column = j;                         // стовбець
                    max_element = array[i][j];
                    count += 14;
                }
                count += 21;
            }
        }
        for (int j = 0; j < array.length; j++) {        // додатні та від'ємні значення в стовбці        
            if (array[j][column] > 0) 
                column_plus += 1;
            else if  (array[j][column] < 0)
                column_minus += 1;
            count += 15;
        }
        column += 1;  sum_plus = left_plus + right_plus; sum_minus = left_minus + right_minus;
        return new int[] {max_element,column,count,sum_plus,column_plus,left_plus,right_plus,sum_minus,column_minus,left_minus,right_minus};
    }

    public int[][] task2(int[][] array, int number) {
        int[][] array_sdvig = new int[array.length][array[0].length]; int sdvig;
                                                                            // создание и присвоение i, j                (4)
        for (int i = 0; i < array.length; i++) {                            // i, <, arr, length, i+=1 -> 3              (7)
            for (int j = 0; j < array[0].length; j++) {                    // j, <, arr[0], length, j+=1 -> 3            (7)
                sdvig = j + number;              // сдвиг на n елементів      sdvig, =, j, +, number                     (5)
                if (sdvig >= array.length)                                 // if, sdvig, >=, arr, length                 (5)
                    sdvig %= array.length;                                 // sdvig, %=, arr, length                     (4)                    
                array_sdvig[i][sdvig] = array[i][j];                       // arr_sdvig, i, sdvig, =, arr, i, j,         (7)
                                           
            }
        }
        return array_sdvig;
    } 
}
