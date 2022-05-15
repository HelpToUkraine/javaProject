package Lab2_PSA;

import java.util.Scanner;

public class User_main {
    public static void main(String[] args) {
        Task_Lab1 object = new Task_Lab1();

        int task = 0, var = 0, sdvig = 10, size = 0; long finish = 0;
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
        if (input.hasNextInt()) {
            var = input.nextInt();
            if (var < 0) {
                System.out.println("Error: Негативный размер массива");
                System.exit(0);
            }
        }
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
        int[][] array = object.initialize(size);           // ініціалізація масиву
        object.print_array(array);
        long start = System.nanoTime();
        switch (task) {
            case 1:        
                int[] res = object.task1(array);
                finish = System.nanoTime();
                System.out.printf("\nМаксимальний елемент масиву: %d | Стовбець: №%d | Iтерацiй: %d",res[0],res[1],res[2]);
                System.out.printf("\n\nДодатнi елементи [масиву]: %d\t-->\tCтовбця №%d: %d\nЛiворуч: %d\nПраворуч: %d",res[3],res[1],res[4],res[5],res[6]);
                System.out.printf("\n\nВiд'ємнi елементи [масиву]: %d\t-->\tCтовбця №%d: %d\nЛiворуч: %d\nПраворуч: %d",res[7],res[1],res[8],res[9],res[10]);  
                break;

            case 2:
                int[][] array_sdvig = object.task2(array, sdvig);
                finish = System.nanoTime();
                System.out.printf("\nМатрица со сдвигом в право: %d\n", sdvig);
                object.print_array(array_sdvig);
                break;
        }
        long time = finish - start;
        System.out.printf("\nВремя работы: %d н/с", time);
    }
}

