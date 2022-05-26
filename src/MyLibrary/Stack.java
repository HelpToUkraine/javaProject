package MyLibrary;

public class Stack {
    private final Object[] arr; // save elements of stack
    private int top; // top of stack
    private final int size; //total capacity of stack

    public Stack(int size) { // initialize array
        arr = new Object[size];
        this.size = size;
        top = -1;
    }

    public void push(Object data) {
        if (isFull()){
            System.out.println("Error: Stack overFlow");
            System.exit(0);
        }
        arr[++top] = data;
    }

    public Object pop(){
        if (isEmpty()) {
            System.out.println("Error: Stack is Empty");
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

    public Object getTop() {
        return (!isEmpty() ? arr[top] : -1);
    }

    public int getIndexTop() {return top;}

    public void printStack() {
        int temp = top;
        while (temp != -1) System.out.print(arr[temp--] + " ");
        System.out.println();
    }

    public Stack reverse() {
        Stack newStack = new Stack(size);
        while (!isEmpty())  newStack.push(pop());
        return newStack;
    }

    public void clear() {
        while (!isEmpty() )
            arr[top--] = 0;
    }

}
