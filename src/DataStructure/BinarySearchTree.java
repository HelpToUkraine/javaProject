package DataStructure;

public class BinarySearchTree<T> {
    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public static class Node<T> {
        public T value;
        public Node<T> leftChild;
        public Node<T> rightChild;

        public Node(T value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
    }

    public void put(T value) {
        root = put(root, value);
    }

    public Node<T> put(Node<T> current, T value) {
        /* якщо пуста Node<K> -> знайдено місце вставки */
        if (current == null)
            return new Node<>(value);
        /* порівнюємо за алфавітом String value та шукаємо місце вставки нової Node<K> */

        if ((Integer) value < (Integer) current.value)
            current.leftChild = put(current.leftChild, value);

        else if ((Integer) value > (Integer) current.value)
            current.rightChild = put(current.rightChild, value);
        /* елемент є в дереві */
        return current;
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    public void traversePostOrder(Node<T> current) {
        if (current != null) {
            traversePostOrder(current.leftChild);
            traversePostOrder(current.rightChild);
        }

    }

    public T getMin() {
        Node<T> temp = root;
        if (root != null) {
            while (temp.leftChild != null)
                temp = temp.leftChild;
            return temp.value;
        }
        return null;
    }

    public void print() {
        recursivePrint(root);
    }

    /* left subTree -> right subTree -> root */
    public void recursivePrint(Node<T> current) {
        if (current != null) {
            recursivePrint(current.leftChild);
            recursivePrint(current.rightChild);
            System.out.print(current.value + " ");
        }
    }

}
