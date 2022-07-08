package Course_work;

public class LinkedList<T> {
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

    public Node getTail() {
        return tail;
    }

    public class Node {
        public T vertex;
        public Node next;

        public Node(T vertex) {
            this.vertex = vertex;
            next = null;
        }
    }

    public void add(T vertex){
        Node newNode = new Node(vertex);
        if (head == null)
            head = tail = newNode;
        else
            tail.next = tail = newNode;
        size++;
    }

    public void remove(T vertex) {
        Node currentNode = head;
        Node previous = null;

        while (currentNode != null) {
            if (currentNode.vertex == vertex){
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

    public boolean contains(Object key){
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.vertex == key)   return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public Object get(int index) {
        if (0 <= index && index <= size-1) {
            Node currentNode = head;
            int i=0;
            while (currentNode != null) {
                if (i == index)
                    return  currentNode.vertex;
                currentNode = currentNode.next;
                i++;
            }
        }
        return -1;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.vertex);
            current = current.next;
        }
    }

}