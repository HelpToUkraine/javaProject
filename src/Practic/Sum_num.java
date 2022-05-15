package Practic;

import java.lang.Character;

public class Sum_num {
    public static void main(String[] args) {
        String text_1 = "P9y33thon2";
        String text_2 = "1JJa7va";
        String text_3 = "Java_Developer";

        int result1 = sumDigits(text_1);
        int result2 = sumDigits(text_2);
        int result3 = sumDigits(text_3);
        
        System.out.printf("\nsumDigits: \n%s\t---> %d\n%s \t---> %d\n%s\t---> %d", text_1, result1, text_2, result2, text_3, result3);
    }

    public static int sumDigits(String text) {
        int sum = 0;
        for (int i = 0; i < text.length(); i++)
            if (Character.isDigit(text.charAt(i)))
                sum += Integer.parseInt(String.valueOf(text.charAt(i)));
        return sum;
    }
}