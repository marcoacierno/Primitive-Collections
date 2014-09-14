package com.besaba.revonline.primitivecollections.list;

import com.besaba.revonline.primitivecollections.iterables.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * A List of integers
 *
 * @author Marco
 * @since 1.0
 */
public class ArrayListInt implements IntIterable, RandomAccess {
    private static final ArrayListInt EMPTY = new ArrayListInt(0);

    private transient int[] elementsData;
    private int size;

    /**
     * Creates an ArrayListInt with the provided capacity.
     *
     * @param capacity The start capacity
     * @return The created ArrayListInt
     */
    public static ArrayListInt withCapacity(int capacity) {
        return new ArrayListInt(capacity);
    }

    /**
     * Creates an ArrayListInt taking the values from another ArrayListInt
     *
     * @param fromList The original list
     * @return The ArrayListInt which contains {@see fromList} values
     */
    public static ArrayListInt from(ArrayListInt fromList) {
        ArrayListInt toList = new ArrayListInt(fromList.size);

        IntIterator iterator = fromList.iterator();
        while (iterator.hasNext()) {
            toList.add(iterator.next());
        }

        return toList;
    }

    public static ArrayListInt with(int number, int... numbers) {
        ArrayListInt toList = new ArrayListInt(0);

        toList.elementsData = numbers;
        toList.size = numbers.length;

        toList.addAt(0, number);

        return toList;
    }

    public static ArrayListInt empty() {
        return EMPTY;
    }

    private ArrayListInt(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(capacity + " is not a valid capacity.");
        }

        elementsData = new int[capacity];
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

        if (obj instanceof ArrayListInt) {
            ArrayListInt objList = (ArrayListInt) obj;

            return objList.size == this.size && Arrays.equals(objList.elementsData, this.elementsData);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elementsData);
    }
}
