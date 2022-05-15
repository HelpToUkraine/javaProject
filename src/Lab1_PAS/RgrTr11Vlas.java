package Lab1_PAS;

import java.util.Scanner;

public class RgrTr11Vlas {
    public static void main(String[] args) {

        double[][] matrix = {   {9, -4, 6, 9, -9, 50.8}, 
                                {-2, 6, -10, -6, -1, -5.4}, 
                                {-2, 8, -5, -8, 3, -45.9}, 
                                {7, -9, 1, 5, -1, 134.5},
                                {-4, 10, 1, -9, 0, -141.9}    };

        double[][] startMatrix = new double[matrix.length][matrix[0].length];                    
        for (int i = 0; i < matrix.length; i++) {
            int j = 0;
            while (j < matrix[0].length) {
                startMatrix[i][j] = matrix[i][j];   // еквівалентна матриця для перетворень
                j++;
            }
        }
        double[][] triangleMatrix = task11(matrix); // трикутна матриця
        double[][] result = task12(triangleMatrix); // вектор результатів
        task13(startMatrix);
        task13(triangleMatrix);
        task13(result);

        System.out.println("\n----------------Task2----------------------");
        Scanner input = new Scanner(System.in);
        System.out.println("Введите значения для x1, x2, x3: ");
        int x1 = input.nextInt(), x2 = input.nextInt(), x3 = input.nextInt();
        input.close();
        System.out.printf("Input:\n x1 = %d \n x2 = %d \n x3 = %d", x1, x2, x3);
        int result21 = task2(x1);
        int result22 = task2(x2);
        int result23 = task2(x3);
        System.out.printf("\nOutput:\n Минимальное число для:\n x1 = %d \n x2 = %d \n x3 = %d", result21, result22, result23);
    }
    // перетворення матриці до трикутникового вигляду
    public static double[][] task11(double[][] matrix) { 
        int i = 0;
        do {
            i++;
            for (int j = i; j < matrix[0].length - 1; j++) {
                double divider = matrix[j][i - 1] / matrix[i - 1][i - 1];
                int counter = 0;
                while (counter < matrix[0].length) {
                    matrix[j][counter] = matrix[j][counter] - divider * matrix[i - 1][counter];
                    counter++;
                }
            }
        } while (i < matrix[0].length - 1);
        return matrix;
    }

    public static double[][] task12(double[][] n) { // отримання значень невідомих змінних СЛАР 
        int t = n[0].length - 1;
        double[] result = new double[t];
        int i = t - 1;
        while (i >= 0) {
            result[i] = n[i][t] / n[i][i];
            for (int c = t - 1; c > i; c--)
                result[i] = result[i] - n[i][c] * result[c] / n[i][i];
            i--;
        }
        int counter = 0;
        double[][] vector = new double[t][1]; 
        do {
            vector[counter][0] = result[counter];
            counter++;
        } while (counter < t);
        return vector;
    }

    // виведення результатів розрахунку завдання 1
    public static void task13(double[][] matrix) {
        if (matrix[0].length == 1)
            System.out.println("Вектор результатов:");
        else  {
            boolean isTriangle = true;      // перевірка на трикутну матрицю
            int k = 1;
            do {
                for (int l = 0; l < k; l++) {
                    if (matrix[k][l] != 0) { // перевірка чи matrix[1][0] нулем
                        isTriangle = false;
                        break;
                    }
                }
                k++;
            } while (k < matrix.length);
            if (isTriangle)
                System.out.println("Трикутникова матриця: ");
            else if (matrix[0].length > 1)
                System.out.println("Початкова матриця: ");

        }
        for (int i = 0; i < matrix.length; i++) {           // виведення матриці
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] % 1 == 0)
                    System.out.printf("%10.0f", matrix[i][j]);
                else
                    System.out.printf("%10.3f", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int task2(int x) {
        int min = 9, last_number = 0; 
        while(x != 0) {
            last_number = x % 10;
            if (last_number < min)
                min = last_number;
            x /= 10;
        }
        return min;
    }
}