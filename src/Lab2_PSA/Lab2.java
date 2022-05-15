package Lab2_PSA;

import java.util.Scanner;
import static java.lang.Math.*;

public class Lab2 {
    public static void main(String[] args) {
        int x = 1, n = 10, var = 0; double p = 0;
        System.out.println("Start program with:\n1. Recursion\n2. Iteration");
        Scanner input = new Scanner(System.in);

        try {
            var = input.nextInt();
        }
        catch (Exception e) {
            System.out.println("Error input");
            System.exit(0);
        }

        long start = System.nanoTime();
        switch (var){
            case 1:
                p = recursion(x, n);
                break;
            case 2:
                p = iteration(x, n);
                break;
            default:
                System.exit(0);
        }

        long finish = System.nanoTime(), time = finish - start;
        System.out.printf("\nTime work: %d н/с", time);
        System.out.printf("\nResult: %.10f", p);
    }

    public static double recursion(int x, int n) {
        if (x > n)
            return 1;
        return sin(x) / (pow(x, 2) + 1) * recursion(x + 1, n);
    }

    public static double iteration(int x, int n) {
        double result = 1;
        for (; x <= n ; x++)
            result *= sin(x) / (pow(x, 2) + 1);
        return result;
    }
}


