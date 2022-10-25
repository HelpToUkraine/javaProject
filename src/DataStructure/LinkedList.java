package DataStructure;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public static class Node {
        public Object data;
        public Node next;

        public Node(Object data) {
            this.data = data;
            next = null;
        }
    }

    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null)
            head = tail = newNode;
        else
            tail.next = tail = newNode;
        size++;
    }

    public void addFirst(Object data) {
        Node newNode = new Node(data);
        if (head == null)
            tail = newNode;
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void removeLast() {
        Node currentNode = head;
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

    public void remove(Object data) {
        Node currentNode = head;
        Node previous = null;

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
            Node currentNode = head;
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
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == key) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public Node getNode(Object data) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == data) return currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public Object get(int index) {
        if (0 <= index && index <= size - 1) {
            Node currentNode = head;
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
