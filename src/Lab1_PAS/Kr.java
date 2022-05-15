package Lab1_PAS;

import static java.lang.Math.*;

public class Kr {
    public static void main(String[] args) {
        System.out.println("\n================Task1================");
        String text1 = "zipXzap";
        String text2 = "zopzop";
        String text3 = "zzzopzop";
        System.out.printf("Input:\n1) %s\n2) %s\n3) %s", text1, text2, text3); 

        String res1 = zipZap(text1);
        String res2 = zipZap(text2);
        String res3 = zipZap(text3);
        System.out.printf("\n\nOutput:\n1) %s\n2) %s\n3) %s", res1, res2, res3);

        // Сума ряда
        System.out.println("\n\n================Task2================");
        double x = 0.8, k = 4;
        double res4 = task2(x);
        System.out.printf("Input: \nx = %.1f\nk != %.0f", x, k); 
        System.out.printf("\n\nOutput:\nСума ряда Z: \n при х = %.1f: ---> %.3f", x, res4);
    }
    public static String zipZap(String text) {
        for (int i = 0; i < text.length() - 2; i++) {
            String substr = text.substring(i, i + 3);
            char res[] = substr.toCharArray();
            if (res[0] == 'z' && res[2] == 'p') 
                text = text.replace(substr, "zp");    
        }
        return text;
    }
    public static double task2(double x) {
        double sum = 0, k = 1;
        while (k <= 6) {          // Перебор чисел k для ряда Z 
            if (k == 4) {
                k++;
                continue;
            }
            double factorial = 1;
            for (int i = 1; i <= k - 1; i++)     // Подставление k для !
                factorial *= i;
            sum += (factorial) / (pow(x + 2 , k)); 
            k++;         
        } 
        return sum; 
    }
}

