package Course_work;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public static class Node {
        public String key;
        public String value;
        public Node leftChild;
        public Node rightChild;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
    }

    public void put(String key, String value) {
        root = put(root, key, value);
    }

    public Node put(Node current, String key, String value) {
        /* якщо пуста Node -> знайдено місце вставки */
        if (current == null)
            return new Node(key, value);
        /* порівнюємо за алфавітом String value та шукаємо місце вставки нової Node */
        int cmp = key.compareToIgnoreCase(current.key);
        if (cmp < 0)
            current.leftChild = put(current.leftChild, key, value);
        else if (cmp > 0)
            current.rightChild = put(current.rightChild, key, value);
        /* елемент є в дереві */
        return current;
    }

    /* cyclic find of value by key */
    public String getValue(String key) {
        Node temp = root;
        while (temp != null) {
            int cmp = key.compareToIgnoreCase(temp.key);
            if (cmp < 0)        temp = temp.leftChild;
            else if (cmp > 0)   temp = temp.rightChild;
            else                return temp.value;
        }
        return null;
    }

    /* recursive find of value by key */
    public String getValueRecursive(String key) {
        return getValueRecursive(root, key);
    }

    public String getValueRecursive(Node current, String key) {
        if (current == null)
            return null;
        int cmp = key.compareToIgnoreCase(current.key);
        if (cmp == 0)
            return current.value;

        return cmp < 0
                ? getValueRecursive(current.leftChild, key)
                : getValueRecursive(current.rightChild, key);
    }

    public void print() {
        recursivePrint(root);
    }

    /* left subTree -> root -> right subTree (DFS) */
    public void recursivePrint(Node current) {
        if (current != null) {
            recursivePrint(current.leftChild);
            System.out.println(current.key + " -> " + current.value);
            recursivePrint(current.rightChild);
        }
    }

}
