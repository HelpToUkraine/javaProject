package MyLibrary;

public class Stack {
    private final int[] arr; // save elements of stack
    private int top; // top of stack
    private final int size; //total capacity of stack

    public Stack(int size) { // initialize array
        arr = new int[size];
        this.size = size;
        top = -1;
    }

    public void push(int data) {
        if (isFull()){
            System.out.println("Stack overFlow");
            System.exit(0);
        }
        arr[++top] = data;
    }

    public int pop(){
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            System.exit(0);
        }
        return arr[top--];
    }

    public boolean isFull() {
        return  top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getSize() {
        return top + 1;
    }

    public int getTop() {
        return (!isEmpty() ? arr[top] : -1);
    }

    public void printStack() {
        while (!isEmpty())
            System.out.print(pop() + " ");
    }

    public void clear() {
        while (!isEmpty() )
            arr[top--] = 0;
    }

}
