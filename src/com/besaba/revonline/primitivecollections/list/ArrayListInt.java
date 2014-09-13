package com.besaba.revonline.primitivecollections.list;

import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * @author Marco
 * @since 1.0
 */
public class ArrayListInt implements IntIterable, RandomAccess {
    private transient int[] elementsData;
    private int size;

    public static ArrayListInt withCapacity(int capacity) {
        return new ArrayListInt(capacity);
    }

    public static ArrayListInt from(ArrayListInt fromList) {
        ArrayListInt toList = new ArrayListInt(fromList.size);

        IntIterator iterator = fromList.iterator();
        while (iterator.hasNext()) {
            toList.add(iterator.next());
        }

        return toList;
    }

    private ArrayListInt(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(capacity + " is not a valid capacity.");
        }

        elementsData = new int[capacity];
    }

    public void add(int value) {
        ensureCapacity(value);

        elementsData[size++] = value;
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
                if (pointer + 1 >= size) {
                    throw new NoSuchElementException();
                }

                return elementsData[pointer++];
            }
        };
    }
}
