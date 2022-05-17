package MyLibrary;

public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public void add(int data){
        Node newNode = new Node(data);
        Node currentNode = head;

        if (head == null)
            head = newNode;
        else {
             while (currentNode.next != null)
                 currentNode = currentNode.next;
             currentNode.next = newNode;
        }
        size++;
    }

    public void addFirst(int data){
        Node newNode = new Node(data);
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
        if (head != null){
            head = head.next;
            size--;
        }
    }

    public void remove(int data) {
        Node currentNode = head;
        Node previous = null;

        while (currentNode != null) {
            if (currentNode.data == data){
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
        if (size != 0) {
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
        head = null;
        size = 0;
    }
    public boolean contains(int key){
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == key)   return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public Node getNode(int data) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == data)       return currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public int get(int index) {
        if (0 <= index && index <= size-1) {
            Node currentNode = head;
            int i=0;
            while (currentNode != null){
                if (i == index)
                    return  currentNode.data;
                currentNode = currentNode.next;
                i++;
            }
        }
        return -1;

    }

}
