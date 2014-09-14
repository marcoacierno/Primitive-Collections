package com.besaba.revonline.primitivecollections.list;

import com.besaba.revonline.primitivecollections.iterables.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * A List of integers
 *
 * @author Marco
 * @since 1.0
 */
public class IntArrayList implements IntIterable, RandomAccess {
    private transient int[] elementsData;
    private int size;

    /**
     * Creates an ArrayListInt with the provided capacity.
     *
     * @param capacity The start capacity
     * @return The created ArrayListInt
     */
    public static IntArrayList withCapacity(int capacity) {
        return new IntArrayList(capacity);
    }

    /**
     * Creates an ArrayListInt taking the values from another ArrayListInt
     *
     * @param fromList The original list
     * @return The ArrayListInt which contains {@see fromList} values
     */
    public static IntArrayList from(IntArrayList fromList) {
        IntArrayList toList = new IntArrayList(fromList.size);

        IntIterator iterator = fromList.iterator();
        while (iterator.hasNext()) {
            toList.add(iterator.next());
        }

        return toList;
    }

    public static IntArrayList with(int number, int... numbers) {
        IntArrayList toList = new IntArrayList(0);

        toList.elementsData = numbers;
        toList.size = numbers.length;

        toList.addAt(0, number);

        return toList;
    }

    public static IntArrayList empty() {
        return IntArrayList.withCapacity(0);
    }

    private IntArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(capacity + " is not a valid capacity.");
        }

        elementsData = new int[capacity];
//        size = capacity;
    }

    /**
     * Adds an integer at the end of the list.
     *
     * @param value The int to add
     */
    public void add(int value) {
        ensureCapacity(size + 1);

        elementsData[size++] = value;
    }

    /**
     * Adds an integer in the position provided in the {@see index}.
     *
     * @param index The position
     * @param value The int to add
     */
    public void addAt(int index, int value) {
        rangeCheckAdd(index);
        ensureCapacity(size + 1);

        System.arraycopy(   elementsData, index,
                            elementsData, index + 1,
                            size - index
                        );

        elementsData[index] = value;
        size++;
    }

    public void addAll(IntArrayList fromList) {
        ensureCapacity(size + fromList.size);

        System.arraycopy(fromList.elementsData, 0, elementsData, size, fromList.size);
        size += fromList.size;
    }

    public void addAllAt(int startIndex, IntArrayList fromList) {
        rangeCheckAdd(startIndex);
        ensureCapacity(size + fromList.size);

        int numMoved = size - startIndex;
        if (numMoved > 0) {
            System.arraycopy( elementsData, startIndex,
                              elementsData, startIndex + fromList.size,
                              numMoved
                            );
        }

        System.arraycopy(   fromList.elementsData, 0,
                            elementsData, startIndex,
                            fromList.size
                        );

        size += fromList.size;
    }

    public int removeAt(int index) {
        rangeCheckAdd(index);

        int from = size - index - 1;
        if (from > 0) {
            System.arraycopy(elementsData, index + 1, elementsData, index, from);
        }

        --size;
        return elementsData[index];
    }

    public int get(int index) {
        return elementsData[index];
    }

    private void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index + " is not a valid index");
        }
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementsData.length;

        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) / 3 + 1;

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            elementsData = Arrays.copyOf(elementsData, newCapacity);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; ++i) {
            if (elementsData[i] == value) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public IntIterator iterator() {
        return new IntIterator() {
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public int next() {
                if (pointer + 1 > size) {
                    throw new NoSuchElementException();
                }

                return elementsData[pointer++];
            }
        };
    }

    public int[] asArray() {
        return elementsData.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj instanceof IntArrayList) {
            IntArrayList objList = (IntArrayList) obj;

            return objList.size == this.size && Arrays.equals(objList.elementsData, this.elementsData);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elementsData);
    }

    @Override
    public String toString() {
        return "Size: " + size + ", Values: " + Arrays.toString(elementsData);
    }
}
