package Lab1_PAS;
import java.util.Scanner;

public class Task1_PZ3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nВведите 3 значения для массива 1 {a, b, c}: ");
        int a = input.nextInt(), b = input.nextInt(), c = input.nextInt();
        System.out.println("Введите 3 значения для массива 2 {z, x, d}: ");
        int z = input.nextInt(), x = input.nextInt(), d = input.nextInt();
        input.close();
        int[][] middleWay = {{a, b, c}, {z, x, d}};
        System.out.println("Маcив з середніх елементів ---> [" + middleWay[0][1] + "," + middleWay[1][1] + "]");
        String text = "helo";
        char[] name = text.toCharArray();
        for (int i = 0; i < name.length; i++) {
            System.out.println(name[i]);
        }
    }
}

