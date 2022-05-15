package Lab1_PAS;

public class Pz7 {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        int n = 45;
        System.out.printf("\nНайти елемент чисел Фибоначи: %d", n);

        if (n <= 45) {
            long result = recursion(n);
            System.out.printf("\nРезультат работы через рекурсию: %d", result);
        }
        else {
            System.out.printf("\nРезультат работы через цикл:\n");
            itertation(n);
            
        }

        final long endTime = System.currentTimeMillis();
        System.out.printf("\n\nВремя работы в милисекундах: " + (endTime - startTime));
    }

    public static long recursion(int n) {
        
        if (n == 1)
            return 1;
        if (n == 0)
            return 0;     
              
        return recursion(n - 1) + recursion(n - 2);
    }

    public static void itertation(int n) {
        long fib1 = 1, fib2 = 1, temp;
        System.out.printf("%d %d ", fib1, fib2);

        for(int i = 2; i < n; i++) {
            temp = fib2;
            fib2 = fib1 + fib2;
            fib1 = temp;
            System.out.printf("%d ", fib2);

        }
    }
}
