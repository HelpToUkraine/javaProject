package Lab1_PAS;
import static  java.lang.Math.*;

public class Lr21 {
    public static void main(String[] args) {
        // Завдання 1
        double x1 = -2.3, x2 = 0.6, x3 = 4.8, y1, y2, y3, a = 0.2, b = 12.5, z = pow((2.5 + b), 2);
        System.out.println("----------\nInput Task1: \n x1 = " + x1 + "\n x2 = " + x2 + " \n x3 = " + x3);
        System.out.println(" a = " + a + " b = " + b + "  z = " + z);
        y1 = task1(x1, a, b, z);
        y2 = task1(x2, a, b, z);
        y3 = task1(x3, a, b, z);
        System.out.printf("----------\nOutput Task1:\n y1 = %.4f%s%.4f%s%.4f", y1, "\n y2 = ", y2, "\n y3 = ", y3);
        // Завдання 2
        double a1 = -1.2, b1 = 0.75, a2 = 0.4, b2 = 2.4, a3 = 1.1, b3 = 6.1;
        System.out.printf("\n----------\nInput Task2: \n 1) x1 = %.1f%s%.1f%s%.2f", x1, " a1 = ", a1, " b1 = ", b1);
        System.out.printf("\n 2) x1 = %.1f%s%.1f%s%.1f", x1, " a2 = ", a2, "  b2 = ", b2);
        System.out.printf("\n 3) x1 = %.1f%s%.1f%s%.1f", x1, " a3 = ", a3, "  b3 = ", b3);
        System.out.printf("\n 4) x2 = %.1f%s%.1f%s%.2f", x2, "  a1 = ", a1, " b1 = ", b1);
        System.out.printf("\n 5) x2 = %.1f%s%.1f%s%.1f", x2, "  a2 = ", a2, "  b2 = ", b2);
        System.out.printf("\n 6) x2 = %.1f%s%.1f%s%.1f", x2, "  a3 = ", a3, "  b3 = ", b3);
        System.out.printf("\n 7) x3 = %.1f%s%.1f%s%.2f", x3, "  a1 = ", a1, " b1 = ", b1);
        System.out.printf("\n 8) x3 = %.1f%s%.1f%s%.1f", x3, "  a2 = ", a2, "  b2 = ", b2);
        System.out.printf("\n 9) x3 = %.1f%s%.1f%s%.1f", x3, "  a3 = ", a3, "  b3 = ", b3);

        double R1 = task2(x1, a1, b1); // Для 1 рівняння с x1 = -2.3 Правильно
        double R2 = task2(x1, a2, b2); // Для 2 рівняння с x1 = -2.3 Правильно
        double R3 = task2(x1, a3, b3); // Для 3 рівняння с x1 = -2.3 Правильно
        double R4 = task2(x2, a1, b1); // Для 1 рівняння с x2 = 0.6 Правильно Основа логарифму < 0. Не задовольняє ОДЗ
        double R5 = task2(x2, a2, b2); // Для 2 рівняння с x2 = 0.6 Правильно
        double R6 = task2(x2, a3, b3); // Для 3 рівняння с x2 = 0.6 Правильно
        double R7 = task2(x3, a1, b1); // Для 1 рівняння с x3 = 4.8 Правильно
        double R8 = task2(x3, a2, b2); // Для 2 рівняння с x3 = 4.8 Правильно
        double R9 = task2(x3, a3, b3); // Для 3 рівняння с x3 = 4.8 Правильно

        System.out.printf("\n----------\nOutput Task2:\nWhen x = -2.3\n R1 = %.4f%s%.4f%s%.4f", R1, "\n R2 = ", R2, "\n R3 = ", R3);
        System.out.printf("\nWhen x = 0.6\n R4 = %.4f%s%.4f%s%.4f", R4, "\n R5 = ", R5, "\n R6 = ", R6);
        System.out.printf("\nWhen x = 4.8\n R7 = %.4f%s%.4f%s%.4f", R7, "\n R8 = ", R8, "\n R9 = ", R9);
    }
    public static double task1(double x, double a, double b, double z) {
        double y;
        if ((a < x) && (x < log(b)) && (a + b * x > 0)) // ОДЗ: Під коренем вираз > 0
            y = sqrt(a + b * x) + sin(z * x);
        else if ((x >= log(b)) && (a + b * x + z > 0)) // ОДЗ: Основа логарифму > 0
            y = log(a + b * x + z);
        else
            y = a + sin(b * x) + cos(pow(x, 2));
        return y;
    }
    public static double task2(double x, double a, double b) {
        double y, z = log(abs(tan(b * x)));
        if (x <= a)
            y = (a + sin(b * x) + cos(pow(x, 2)));
        else if ((a < x) && (x < log(b)) && (a + b * x > 0)) // ОДЗ: Під коренем вираз > 0
            y = (sqrt(a + b * x) + sin(z * x));
        else
            y = (log(a + b * x + z));
        return y;
    }
}
