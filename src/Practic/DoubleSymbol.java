package Practic;

import java.lang.String;

public class DoubleSymbol {
    public static void main(String[] args) {
        String text_1 = "Python";
        String text_2 = "JJava";
        String text_3 = "Java_Developer";

        String result1 = doubleChar(text_1);
        String result2 = doubleChar(text_2);
        String result3 = doubleChar(text_3);

        System.out.printf("\n%s\n%s\n%s", result1, result2, result3);
    }

    public static String doubleChar(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++)
            result += String.valueOf(text.charAt(i)) + String.valueOf(text.charAt(i));

        return result;
    }
}
