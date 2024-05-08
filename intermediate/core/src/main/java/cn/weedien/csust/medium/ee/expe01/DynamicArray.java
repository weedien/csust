package cn.weedien.csust.medium.ee.expe01;

import java.util.Arrays;

public class DynamicArray<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public static <T> void swap(DynamicArray<T> arr, int i, int j) {
        if (i < 0 || i >= arr.size() || j < 0 || j >= arr.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public static void main(String[] args) {
        DynamicArray<Integer> ints = new DynamicArray<>();
        DynamicArray<Number> numbers = new DynamicArray<>();
        ints.add(100);
        ints.add(34);
        ints.copyTo(numbers);
        System.out.println(numbers);

    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(elementData.length * 2, minCapacity);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void add(T t) {
        ensureCapacity(size + 1);
        elementData[size++] = t;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) elementData[index];
    }

    public int size() {
        return size;
    }

    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        elementData[index] = item;
    }

    public void addAll(DynamicArray<? extends T> other) {
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }

    public void copy(DynamicArray<? extends T> source) {
        elementData = Arrays.copyOf(source.elementData, source.size);
        size = source.size;
    }

    public void copyTo(DynamicArray<? super T> dest) {
        for (int i = 0; i < size; i++) {
            dest.add(this.get(i));
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elementData[i]).append(", ");
        }
        sb.append(elementData[size - 1]).append("]");
        return sb.toString();
    }
}
