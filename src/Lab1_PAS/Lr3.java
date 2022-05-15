package Lab1_PAS;

import static java.lang.Math.*;
import java.util.Scanner;
import java.util.Arrays;


public class Lr3 {
    public static void main(String[] args) {
        // Завдання 1
        float x_start = -2, x_end = 5, h = 0.2F;
        task1(x_start, x_end, h);

        // Завдання 2 (просте присвоєння 10 значень масиву)
        int array1[] = {5, -9, 3, 11, 1, -6, -7, 2, 31, 7};
        int array2[] = {1, -5, 7, 14, 6, -3, -9, 6, 14, 0};
        int result1[] = task2(array1);
        int result2[] = task2(array2);
        System.out.println("\n------------------Task2------------------------");  
        System.out.printf("Input: \n(1) %s", Arrays.toString(array1));
        System.out.printf("\n(2) %s", Arrays.toString(array2));
        System.out.printf("\n\nOutput: \n(1) Сумма вiд'ємних чисел = %d\t| Кiлькiсть додатних елементiв: %d", result1[0], result1[1]);
        System.out.printf("\n(2) Сумма вiд'ємних чисел = %d\t| Кiлькiсть додатних елементiв: %d", result2[0], result2[1]); 
        
        // Завдання 3 'ввод с клавиатуры x1 = 0.6 && x2 = 2.8'
        System.out.println("\n\n------------------Task3------------------------");
        System.out.println("Введите значения для x1, x2: ");
        Scanner input = new Scanner(System.in);
        double x1 = input.nextDouble(), x2 = input.nextDouble();
        input.close();
        double result3_1 = task3(x1);
        double result3_2 = task3(x2);
        System.out.printf("Input: \nx1 = %.1f \nx2 = %.1f", x1, x2); 
        System.out.printf("\n\nOutput: \nСумма ряда Z: \n при х1 = %.1f: %.4f \n при х2 = %.1f: %.4f", x1, result3_1, x2, result3_2); 
    }

    public static void task1(float x1, float x2, float h) { 
        System.out.println("------------------Task1------------------------\nOutput:\t\ty = f(x)\tz = f(x)");
        while(x1 <= x2) {
            double y = 2.5 * sin(x1 / 2);
            double z = sin(x1) / log(x1 + 4);
            System.out.printf("\nx = %.1f \ty = %.4f \tz = %.4f", x1, y, z);
            x1 += h;
        }
    }    

    public static int[] task2(int[] array) {
        int sum = 0, total = 0;
        for (int i : array) {
            if (i > 0)
                total += 1;
            else
                sum += i; 
        }
        return new int[] {sum, total};            
    }
    
    public static double task3(double x) {
        double sum = 0, k = 1;
        while (k <= 5) {          // Перебор чисел k для ряда Z 
            double factorial = 1;
            for (int i = 1; i <= 2 * k; i++)     // Подставление k для !
                factorial *= i;
            sum += (pow(-1, k) * factorial) / (4.5 * pow(x, 2 * k - 1)); 
            k++;         
        } 
        return sum; 
    }
}  