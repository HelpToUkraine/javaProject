package Course_work;

public class BinaryTree {
    private NodeTree root;

    public BinaryTree() {
        root = null;
    }

    public static class NodeTree {
        public String key;
        public String value;
        public int id;
        public boolean isVisited;
        public NodeTree leftChild;
        public NodeTree rightChild;

        public NodeTree(String key, String value, int id) {
            this.id = id;
            this.key = key;
            this.value = value;
            isVisited = false;
            leftChild = null;
            rightChild = null;
        }
    }

    public void put(String key, String value, int id) {
        root = put(root, key, value, id);
    }

    public NodeTree put(NodeTree current, String key, String value, int id) {
        /* якщо пуста NodeTree -> знайдено місце вставки */
        if (current == null)
            return new NodeTree(key, value, id);
        /* порівнюємо за алфавітом String value та шукаємо місце вставки нової NodeTree */
        int cmp = key.compareToIgnoreCase(current.key);
        if (cmp < 0)
            current.leftChild = put(current.leftChild, key, value, id);
        else if (cmp > 0)
            current.rightChild = put(current.rightChild, key, value, id);
        /* елемент є в дереві */
        return current;
    }

    /* cyclic find of value by key */
    public String getValue(String key) {
        NodeTree temp = root;
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

    public String getValueRecursive(NodeTree current, String key) {
        if (current == null)
            return null;
        int cmp = key.compareToIgnoreCase(current.key);
        if (cmp == 0)
            return current.value;

        return cmp < 0
                ? getValueRecursive(current.leftChild, key)
                : getValueRecursive(current.rightChild, key);
    }

    public void traverseInOrder(Graph graph) {
        traverseInOrder(graph, root);
    }

    public void traverseInOrder(Graph graph, NodeTree current) {
        if (current != null) {
            traverseInOrder(graph, current.leftChild);
            graph.addVertex(current);
            traverseInOrder(graph, current.rightChild);
        }
    }

    public void print() {
        recursivePrint(root);
    }

    /* left subTree -> root -> right subTree (DFS) */
    public void recursivePrint(NodeTree current) {
        if (current != null) {
            recursivePrint(current.leftChild);
            System.out.println(current.id + " -> " + current.key + " -> " + current.value);
            recursivePrint(current.rightChild);
        }
    }

}
