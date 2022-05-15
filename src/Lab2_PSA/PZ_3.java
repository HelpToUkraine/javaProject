package Lab2_PSA;

import java.util.ArrayList;
import java.util.Random;

public class PZ_3 {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            a.add(random.nextInt(20)-10);
            if (a.get(i) > 0) b.add(a.get(i));
        }
        sortInsertion(b);
        System.out.println("Array a: " + a);
        System.out.println("Sorted array b of positive elements a: " + b);
    }

    public static void sortInsertion(ArrayList<Integer> b){
        for (int i = 1; i < b.size(); ++i) {
            int key = b.get(i);
            int j = i - 1;
            for (; j >= 0 && b.get(j) > key; j--)
                b.set(j + 1, b.get(j));
            b.set(j + 1, key);
        }
    }
}
