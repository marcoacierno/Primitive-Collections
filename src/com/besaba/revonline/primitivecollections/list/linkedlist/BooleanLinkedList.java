package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.BooleanConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.BooleanIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.BooleanIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class BooleanLinkedList implements BooleanIterable, Cloneable {
    private transient Node header = new Node((boolean)false, null, null);
    private transient int size;

    private BooleanLinkedList() {
        header.next = header.previous = header;
    }

    public static BooleanLinkedList empty() {
        return new BooleanLinkedList();
    }

    public static BooleanLinkedList with(boolean... values) {
        BooleanLinkedList list = new BooleanLinkedList();

        for(boolean value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(boolean value) {
        addBefore(value, header);
    }

    public void addFirst(boolean value) {
        addBefore(value, header.next);
    }

    public boolean get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public boolean getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public boolean getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public boolean set(int index, boolean value) {
        Node prev = lookupNode(index);

        boolean oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public boolean removeAt(int index) {
        return remove(lookupNode(index));
    }

    public boolean removeFirst() {
        return remove(header.next);
    }

    public boolean removeLast() {
        return remove(header.previous);
    }

    public int indexOf(boolean value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(boolean value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public boolean[] asArray() {
        final boolean[] array = new boolean[size];

        forEach(new BooleanConsumer() {
            private int index = 0;

            @Override
            public void accept(boolean value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(BooleanConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(boolean value, Node before) {
        Node newNode = new Node(value, before, before.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (Node node = header; node != null; ) {
            Node next = node.next;

            node.next = null;
            node.previous = null;

            node = next;
        }

        header = new Node((boolean)false, null, null);
        header.next = header.previous = header;
        size = 0;
    }

    private boolean remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        boolean oldValue = node.value;

        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.next = node.previous = null;

        size--;
        return oldValue;
    }

    private Node lookupNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Size: " + size + ", Index: " + index);
        }

        Node e = header;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; ++i) {
                e = e.next;
            }
        } else {
            for (int i = size; i > index; --i) {
                e = e.previous;
            }
        }
        return e;
    }

    @Override
    public BooleanIterator iterator() {
        return new BooleanIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public boolean next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private boolean value;

        private Node(boolean value, Node next, Node previous) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj instanceof BooleanLinkedList) {
            BooleanLinkedList linkedList = (BooleanLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            BooleanIterator iterator1 = this.iterator();
            BooleanIterator iterator2 = linkedList.iterator();

            while (iterator1.hasNext() && iterator2.hasNext()) {
                if (iterator1.next() != iterator2.next()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        BooleanIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        BooleanIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    public BooleanLinkedList clone() {
        final BooleanLinkedList linkedList;

        try {
            linkedList = (BooleanLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((boolean)false, null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new BooleanConsumer() {
            @Override
            public void accept(boolean value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}
