package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.DoubleConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.DoubleIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.DoubleIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class DoubleLinkedList implements DoubleIterable, Cloneable {
    private transient Node header = new Node((double)0.0d, null, null);
    private transient int size;

    private DoubleLinkedList() {
        header.next = header.previous = header;
    }

    public static DoubleLinkedList empty() {
        return new DoubleLinkedList();
    }

    public static DoubleLinkedList with(double... values) {
        DoubleLinkedList list = new DoubleLinkedList();

        for(double value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(double value) {
        addBefore(value, header);
    }

    public void addFirst(double value) {
        addBefore(value, header.next);
    }

    public double get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public double getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public double getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public double set(int index, double value) {
        Node prev = lookupNode(index);

        double oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public double removeAt(int index) {
        return remove(lookupNode(index));
    }

    public double removeFirst() {
        return remove(header.next);
    }

    public double removeLast() {
        return remove(header.previous);
    }

    public int indexOf(double value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(double value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public double[] asArray() {
        final double[] array = new double[size];

        forEach(new DoubleConsumer() {
            private int index = 0;

            @Override
            public void accept(double value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(DoubleConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(double value, Node before) {
        Node newNode = new Node(value, before, before.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (Node node = header; node != null; ) {
            Node next = node.next;

            node.next = null;
            node.previous = null;

            node = next;
        }

        header = new Node((double)0.0d, null, null);
        header.next = header.previous = header;
        size = 0;
    }

    private double remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        double oldValue = node.value;

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
    public DoubleIterator iterator() {
        return new DoubleIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public double next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private double value;

        private Node(double value, Node next, Node previous) {
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

        if (obj instanceof DoubleLinkedList) {
            DoubleLinkedList linkedList = (DoubleLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            DoubleIterator iterator1 = this.iterator();
            DoubleIterator iterator2 = linkedList.iterator();

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
        DoubleIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        DoubleIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    public DoubleLinkedList clone() {
        final DoubleLinkedList linkedList;

        try {
            linkedList = (DoubleLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((double)0.0d, null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new DoubleConsumer() {
            @Override
            public void accept(double value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}
