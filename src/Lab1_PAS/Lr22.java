package Lab1_PAS;
import java.util.Scanner;

public class Lr22 {
    public static void main(String[] args) {
        // Введення даних для завдання 3
        System.out.println("\nВведите координаты точки А1, A2, A3 ---> (x1,y1) (x2,y2) (x3,y3): ");
        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt(), y1 = input.nextInt(), x2 = input.nextInt(), y2 = input.nextInt(), x3 = input.nextInt(), y3 = input.nextInt();
        input.close();
        System.out.println("----------\nInput Task3: \n x1 = "+x1+"; y1 = "+y1+" \n x2 = "+x2+"; y2 = "+y2+"\n x3 = "+x3+"; y3 = "+y3);
        String A1 = task3(x1, y1);
        String A2 = task3(x2, y2);
        String A3 = task3(x3, y3);
        System.out.printf("\nOutput: \n Точка A1 з координатами: (%d%s %d%s %s %s", x1, ",", y1,") ---->", A1, "I чверті\n");
        System.out.printf(" Точка A2 з координатами: (%d%s %d%s %s %s", x2, ",", y2,") ---->", A2, "I чверті\n");
        System.out.printf(" Точка A3 з координатами: (%d%s %d%s %s %s", x3, ",", y3,") ---->", A3, "I чверті\n----------\n");

        // Введення даних для завдання 4
        double a = -2.0, b = 1.2, c = 9.5, a1 = 0.5, b1 = 2, c1 = -0.15, a2 = 0.4, b2 = 2.2, c2 = 9.5;
        System.out.println("Input Task4: \n a = "+a+" b = "+b+" c = "+c+"\n a1 = "+a1+" b1 = "+b1+" c1 = "+c1+"\n a2 = "+a2+" b2 = "+b2+" c2 = "+c2);
        String A = task4(a, b, c);
        String B = task4(a1, b1, c1);
        String C = task4(a2, b2, c2);
        System.out.printf("\nOutput:\n%s%.4f%s%s%.4f%s%s%.4f", A, (a+b+c)/3, "\n", B, (a1+b1+c1)/3, "\n", C, (a2+b2+c2)/3);

    }
    public static String task3(int x, int y){
        if (x > 0 && y > 0)
            return("Належить");
        else
            return("Не належить");
    }

    public static String task4(double a, double b, double c) {
        double average = (a + b + c) / 3;
        if ((Math.min(a,b) < average) && (Math.min(b,c) < average))
            return (" Число: " + Math.min(a,b) + "; " + Math.min(b,c) + " < середнього арифметичного = ");
        else
            return "";
    }
}