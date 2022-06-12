package MyLibrary;

public class JenericBinaryTree<K, V> {
    private Node<K, V> root;

    public JenericBinaryTree() {
        root = null;
    }

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> leftChild;
        public Node<K, V> rightChild;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node<K, V> put(Node<K, V> current, K key, V value) {
        /* якщо пуста Node<K> -> знайдено місце вставки */
        if (current == null)
            return new Node<>(key, value);
        /* порівнюємо за алфавітом String value та шукаємо місце вставки нової Node<K> */
        String keyStr = (String)key;
        int cmp = keyStr.compareToIgnoreCase((String)current.key);
        if (cmp < 0)
            current.leftChild = put(current.leftChild, key, value);
        else if (cmp > 0)
            current.rightChild = put(current.rightChild, key, value);
        /* елемент є в дереві */
        return current;
    }

    /* cyclic find of value by key */
    public V getValue(K key) {
        Node<K, V> temp = root;
        while (temp != null) {
            String keyStr = (String)key;
            int cmp = keyStr.compareToIgnoreCase((String)temp.key);
            if (cmp < 0)        temp = temp.leftChild;
            else if (cmp > 0)   temp = temp.rightChild;
            else                return temp.value;
        }
        return null;
    }

    /* recursive find of value by key */
    public V getValueRecursive(K key) {
        return getValueRecursive(root, key);
    }

    public V getValueRecursive(Node<K, V> current, K key) {
        if (current == null)
            return null;
        String keyStr = (String)key;
        int cmp = keyStr.compareToIgnoreCase((String)current.key);
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
    public void recursivePrint(Node<K, V> current) {
        if (current != null) {
            recursivePrint(current.leftChild);
            System.out.println(current.key + " -> " + current.value);
            recursivePrint(current.rightChild);
        }
    }
}
