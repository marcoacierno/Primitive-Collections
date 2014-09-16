package com.besaba.revonline.primitivecollections.list.arraylist;

import com.besaba.revonline.primitivecollections.function.BooleanConsumer;
import com.besaba.revonline.primitivecollections.iterables.BooleanIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.BooleanIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * A List
 *
 * @author Marco
 * @since 1.0
 */
public class BooleanArrayList
        implements BooleanIterable, RandomAccess, Cloneable {
    private transient boolean[] elementsData;
    private int size;

    /**
     * Creates an ArrayListInt with the provided capacity.
     *
     * @param capacity The start capacity
     * @return The created ArrayListInt
     */
    public static BooleanArrayList withCapacity(int capacity) {
        return new BooleanArrayList(capacity);
    }

    /**
     * Creates an ArrayListInt taking the values from another ArrayListInt
     *
     * @param fromList The original list
     * @return The ArrayListInt which contains {@see fromList} values
     */
    public static BooleanArrayList from(BooleanArrayList fromList) {
        BooleanArrayList toList = new BooleanArrayList(0);
        toList.elementsData = fromList.asArray();
        toList.size = fromList.size;

        return toList;
    }


    /**
     * Creates an  with the values provided as argument
     *
     * @param number The list need at least one value to be created,
     *               {@see empty()} to create an empty list.
     * @param numbers Other values to insert
     * @return An IntArrayList with the values provided as arguments
     */
    public static BooleanArrayList with(boolean number, boolean... numbers) {
        BooleanArrayList toList = new BooleanArrayList(0);

        toList.elementsData = numbers;
        toList.size = numbers.length;

        toList.addAt(0, number);

        return toList;
    }

    /**
     * Creates an empty IntArrayList
     * @return An empty list
     */
    public static BooleanArrayList empty() {
        return BooleanArrayList.withCapacity(0);
    }

    private BooleanArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(capacity + " is not a valid capacity.");
        }

        elementsData = new boolean[capacity];
    }

    /**
     * Adds at the end of the list.
     *
     * @param value The int to add
     */
    public void add(boolean value) {
        ensureCapacity(size + 1);

        elementsData[size++] = value;
    }

    /**
     * Adds in the position provided in the {@see index}.
     *
     * @param index The position
     * @param value The int to add
     */
    public void addAt(int index, boolean value) {
        rangeCheckAdd(index);
        ensureCapacity(size + 1);

        System.arraycopy(   elementsData, index,
                            elementsData, index + 1,
                            size - index
                        );

        elementsData[index] = value;
        size++;
    }

    public boolean set(int index, boolean newValue) {
        rangeCheckAdd(index);

        boolean oldValue = elementsData[index];
        elementsData[index] = newValue;
        return oldValue;
    }

    /**
     * Adds in the List all the values inside {@see fromList}
     * @param fromList The list which contains the value to add
     */
    public void addAll(BooleanArrayList fromList) {
        ensureCapacity(size + fromList.size);

        System.arraycopy(fromList.elementsData, 0, elementsData, size, fromList.size);
        size += fromList.size;
    }

    /**
     * Adds all the values of {@see fromList} from the index {@see startIndex}
     *
     * @param startIndex The index from where the values should be added
     * @param fromList The list which contains the value to add
     */
    public void addAllAt(int startIndex, BooleanArrayList fromList) {
        rangeCheckAdd(startIndex);
        ensureCapacity(size + fromList.size);

        int numMoved = size - startIndex;
        if (numMoved > 0) {
            System.arraycopy( elementsData, startIndex,
                              elementsData, startIndex + fromList.size,
                              numMoved
                            );
        }

        System.arraycopy(fromList.elementsData, 0,
                elementsData, startIndex,
                fromList.size
        );

        size += fromList.size;
    }

    /**
     * Removes the value at the index provided as argument
     *
     * @param index The index to remove
     * @return The value which was inside the position
     */
    public boolean removeAt(int index) {
        rangeCheckAdd(index);

        int from = size - index - 1;

        if (from > 0) {
            System.arraycopy(   elementsData, index + 1,
                                elementsData, index,
                                from);
        }

        // instead of make a clean copy of the old array w/o this index
        // i set it to 0. it could cause bugs.
        // asArray has been adapted to avoid bugs but remember this fact
        elementsData[--size] = false;

        return elementsData[index];
    }

    /**
     * Returns the value inside the index passed as argument
     *
     * @param index The index to read
     * @return The value
     */
    public boolean get(int index) {
        return elementsData[index];
    }

    /**
     * Clear the collection
     */
    public void clear() {
        elementsData = new boolean[0];
        size = 0;
    }

    public void forEach(BooleanConsumer consumer) {
        for(boolean value : elementsData) {
            consumer.accept(value);
        }
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

    /**
     * The size of the list
     *
     * @return The size of the list
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(boolean value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(boolean value) {
        for (int i = 0; i < size; ++i) {
            if (elementsData[i] == value) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(boolean value) {
        for (int i = size - 1; i >= 0; --i) {
            if (elementsData[i] == value) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public BooleanIterator iterator() {
        return new BooleanIterator() {
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public boolean next() {
                if (pointer + 1 > size) {
                    throw new NoSuchElementException();
                }

                return elementsData[pointer++];
            }
        };
    }

    public boolean[] asArray() {
        boolean[] array = new boolean[size];

        System.arraycopy(elementsData, 0,
                         array, 0,
                         size);

        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj instanceof BooleanArrayList) {
            BooleanArrayList objList = (BooleanArrayList) obj;

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

    @Override
    protected BooleanArrayList clone() {
        BooleanArrayList clone;

        try {
            clone = (BooleanArrayList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();//never
        }

        clone.elementsData = elementsData.clone();
        return clone;
    }
}
