package DataStructure;

public class JenericLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public JenericLinkedList() {
        head = null;
        tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public static class Node<T> {
        public T data;
        public int id;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }

        public Node(T data, int id) {
            this.data = data;
            this.id = id;
            next = null;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        //Node currentNode = head;
        if (head == null)
            head = tail = newNode;
        else
            tail.next = tail = newNode;
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null)
            tail = newNode;
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void removeLast() {
        Node<T> currentNode = head;
        if (size != 0) {
            if (head.next == null)
                head = null;
            else {
                while (currentNode.next.next != null)
                    currentNode = currentNode.next;
                currentNode.next = null;
            }
        }
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }

    public void remove(T data) {
        Node<T> currentNode = head;
        Node<T> previous = null;

        while (currentNode != null) {
            if (currentNode.data == data) {
                if (currentNode == head)
                    head = currentNode.next;
                else
                    previous.next = currentNode.next;
                size--;
            }

            previous = currentNode;
            currentNode = currentNode.next;
        }
    }

    public void print() {
        if (!isEmpty()) {
            Node<T> currentNode = head;
            while (currentNode != null) {
                System.out.print(currentNode.data + " ");
                currentNode = currentNode.next;
            }
            System.out.println();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean contains(Object key) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == key) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public Node<T> getNode(Object data) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == data) return currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public Object get(int index) {
        if (0 <= index && index <= size - 1) {
            Node<T> currentNode = head;
            int i = 0;
            while (currentNode != null) {
                if (i == index)
                    return currentNode.data;
                currentNode = currentNode.next;
                i++;
            }
        }
        return -1;

    }

}
