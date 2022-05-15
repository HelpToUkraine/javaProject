package Practic;

import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {

        System.out.println("Введите строку:");

        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        input.close();

        String result = stringDeep(text);

        System.out.println("\nСтрока наоборот:" + result);
    
    }

    static String stringDeep(String text) {
        String result = "";
        char[] charr = text.toCharArray();

        for(int i = charr.length - 1; i >= 0; i--) {
            result += charr[i]; 
        }
        return result;
    }
}
