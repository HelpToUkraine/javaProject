package Practic;

import java.util.Scanner;

public class JavaRush {
    
        public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int x = input.nextInt(), y = input.nextInt();
        input.close();

        if (x > 0 && y > 0)
            System.out.println(1);
        else if (x < 0 && y > 0)
            System.out.println(2); 
        else if (x < 0 && y < 0)
            System.out.println(3);
        else
        System.out.println(4); 
    }   
}
