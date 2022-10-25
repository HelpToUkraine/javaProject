package DataStructure;

public class QueueArray {
    private final Object[] queue;
    private int top;
    private final int size;

    public QueueArray(int size) {
        queue = new Object[size];
        this.size = size;
        top = -1;
    }

    public void add(Object data) {
        if (isFull()) {
            System.err.println("Error: Queue overFlow");
            System.exit(0);
        }
        queue[++top] = data;
    }

    public void remove() {
        Object temp = queue[0];
        if (!isEmpty()) {
            for (int i = 1; i <= top; i++)
                queue[i - 1] = queue[i];
            queue[top] = temp;
        }
    }

    public void removeIndexOf(int index) {
        if (index >= 0 && index <= top) {
            Object temp = queue[index];
            for (int i = index + 1; i <= top; i++)
                queue[i - 1] = queue[i];
            queue[top] = temp;
        } else {
            System.err.println("Error: Out of range Queue");
            System.exit(0);
        }
    }


    public Object get(int index) {
        Object temp = null;
        if (0 <= index && index <= top)
            for (int i = 0; i <= top; i++) {
                if (i == index)
                    temp = peek();
                remove();
            }
        return temp != null ? temp : -1;
    }

    public Object peek() {
        return !isEmpty() ? queue[0] : null;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void printQueue() {
        int i = 0;
        while (i != top + 1) {
            System.out.print(queue[i++] + " ");
        }
        System.out.println();
    }
}
