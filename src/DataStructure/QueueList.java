package DataStructure;

public class QueueList {
    private Node head;
    private Node tail;
    private int size;

    public QueueList() {
        head = tail = null;
    }

    public static class Node {
        public Object key;
        public Object value;
        public Node next;

        Node(Object value) {
            this.value = value;
            next = null;
        }

        Node(Object key, Object value) {
            this.value = value;
            this.key = key;
            next = null;
        }
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void add(Object value) {
        Node newNode = new Node(value);
        if (head == null)
            head = tail = newNode;
        else
            tail.next = tail = newNode;
        size++;
    }

    public void add(Object key, Object value) {
        Node newNode = new Node(key, value);
        if (head == null)
            head = tail = newNode;
        else
            tail.next = tail = newNode;
        size++;
    }

    public Node remove() {
        Node temp = peek();
        if (isEmpty()) {
            System.err.println("Error: Queue is empty");
            System.exit(0);
        } else {
            head = head.next;
            size--;
        }
        return temp;
    }

    public Object poll() {
        Node temp = peek();
        if (isEmpty()) {
            System.err.println("Error: Queue is empty");
            System.exit(0);
        } else {
            head = head.next;
            size--;
        }
        return temp.value;
    }

    public void removeIndexOf(int index) {
        if (index < 0 || index >= size) {
            System.err.println("Error: Out of range Queue");
            System.exit(0);
        }
        int i = 0;
        while (i != size + 1) {
            if (i == index)
                remove();
            else
                add(remove());
            i++;
        }
    }

    public Object get(int index) {
        Object temp = null;
        if (0 <= index && index < size)
            for (int i = 0; i < size; i++) {
                if (i == index)
                    temp = peek();
                add(remove());
            }
        return temp != null ? temp : -1;
    }

    public Node peek() {
        return !isEmpty() ? head : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void print() {
        if (!isEmpty()) {
            Node currentNode = head;
            while (currentNode != null) {
                System.out.printf("Key: %5s\t->\tValue: %s\n", currentNode.key, currentNode.value);
                currentNode = currentNode.next;
            }
            System.out.println();
        } else System.out.println("null");
    }

}