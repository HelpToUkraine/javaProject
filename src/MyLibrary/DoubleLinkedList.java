package MyLibrary;

public class DoubleLinkedList {
    private Node head, tail;
    private int size;

    public DoubleLinkedList() {
        head = tail = null;
    }

    public Node getHead() {
        return head;
    }

    public static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

        public void add(int data) {
            Node newNode = new Node(data);
            Node currentNode = head;
            if (head == null) {
                head = tail = newNode;
                head.prev = null;
                tail.next = null;
            }
            else {
                tail.next = newNode; // вставить ноду в кінець
                newNode.prev = tail;    // силка на попередню ноду
                tail = newNode;         // новий хвіст
                tail.next = null;
            }
            size++;
        }

        public void remove(int key) {
            if (head.data == key) {
                head = head.next;
                head.prev = null;
            }
            else {
                Node currentNode = head;
                Node previousNode = null;
                while (currentNode != null) {
                    if (currentNode.data == key) {
                        previousNode.next = currentNode.next;
                        currentNode.next.prev = previousNode;
                    }
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
            size--;
        }

        public boolean contains(int key) {
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.data == key)    return true;
                currentNode = currentNode.next;
            }
            return false;
        }

        public Node getNode(int key) {
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.data == key)    return currentNode;
                currentNode = currentNode.next;
            }
            return null;
        }

        public void print() {
            if (size != 0) {
                Node currentNode = head;
                while (currentNode != null) {
                    System.out.print(currentNode.data + " ");
                    currentNode = currentNode.next;
                }
            }
        }

}

