package MyLibrary;

import static java.util.Objects.checkIndex;

public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_SIZE = 5;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0)
            throw new IllegalArgumentException();
        array = new Object[initCapacity];
    }

    @Override
    public void add(T element) {
        resizeIfNeeded();
        array[size] = element;
        size++;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index, size + 1);
        resizeIfNeeded();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private void resizeIfNeeded() {
        if (size == array.length) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public void set(int index, T element) {
       checkIndex(index, size);
       array[index] = element;
        
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T deleteElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return deleteElement;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element))
                return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        array = new Object[DEFAULT_SIZE];
    }
}
