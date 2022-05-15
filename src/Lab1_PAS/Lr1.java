package Lab1_PAS;

import java.util.Scanner;           // Підключення стандартних бібліотек
import static java.lang.Math.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lr1 {
    public static void main(String[] args) { // введення початкових данних (присвоєння значень змінним)
        calcY(1.1, 0.004, 0.2);      // Виклик методів для розразунку значень та виводу дати
        calcZ(1.1, 0.004, 0.2);
        Scanner input = new Scanner(System.in); // зчитування данних з клавіатури
        System.out.println("\nEnter numbers for a, b, x: ");
        double a = input.nextDouble(), b = input.nextDouble(), x = input.nextDouble();
        input.close();
        calcY(a, b, x);  // Повторний виклик методів calcY, calcZ
        calcZ(a, b, x); 
        outputDate();
    }
    public static void calcY(double a, double b, double x) {         // Створення змінних типу double
        double calcY = pow(sin(pow(x * x + a, 2)), 3) - sqrt(x / b); // функція для обчислення calcY
            System.out.println("\n---------------\nInput: \n a = " + a +" \n b = " + b +" \n x = " + x);
            System.out.printf("Output: \n y = %.4f %n", calcY);
        }
    public static void calcZ(double a, double b, double x) {
        double calcZ = pow(x, 2) / a + cos(pow(x + b, 3));          // функція для обчислення calcZ
            System.out.printf(" z = %.4f %n", calcZ);
    }   
    public static void outputDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        String currentime = dateFormat.format(new Date());
        System.out.println("\nCurrent time is: " + currentime + "\n"); // Вивід форматованої дати та часу
    }
}
