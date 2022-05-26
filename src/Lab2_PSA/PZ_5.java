package Lab2_PSA;

import MyLibrary.Stack;

public class PZ_5 {
    public static void main(String[] args) {
        Stack stack = new Stack(10);

        System.out.println("Task6: зробити “дно” стека вершиною, а вершину – “дном");
        initialize(stack);
        Stack result = task6(stack); // new reverse Stack
        result.printStack();

        System.out.println("\nTask 12: Видалити кожен другий елемент стека (з голови)");
        initialize(stack);
        task12(stack);
        stack.printStack();
    }

    public static void initialize(Stack stack) {
        for (int i = 1; i <= 10 ; i++)
            stack.push(i);
        stack.printStack();
    }

    public static Stack task6(Stack stack) {
        return stack.reverse();
    }

    public static void task12(Stack stack) {
        Stack tempStack = new Stack(stack.getSize());
        int i = 0;
        while (!stack.isEmpty()) {
            if (i % 2 == 0)     tempStack.push(stack.pop());
            else stack.pop();
            i++;
        }
        while (!tempStack.isEmpty()) stack.push(tempStack.pop());
    }

}
