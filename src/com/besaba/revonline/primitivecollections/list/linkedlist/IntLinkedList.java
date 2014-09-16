package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.iterables.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class IntLinkedList implements IntIterable {
    private transient Node header = new Node(0, null, null);
    private transient int size;

    private IntLinkedList() {
        header.next = header.previous = header;
    }

    public static IntLinkedList empty() {
        return new IntLinkedList();
    }

    public static IntLinkedList with(int... values) {
        IntLinkedList list = new IntLinkedList();

        for(int value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(int value) {
        addBefore(value, header);
    }

    public int get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public int getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public int getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public int removeAt(int index) {
        return remove(lookupNode(index));
    }

    public int removeFirst() {
        return remove(header.next);
    }

    public int removeLast() {
        return remove(header.previous);
    }

    private void addBefore(int value, Node before) {
        Node newNode = new Node(value, before, before.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    private int remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        int oldValue = node.value;

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
    public IntIterator iterator() {
        return new IntIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public int next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private int value;

        private Node(int value, Node next, Node previous) {
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

        if (obj instanceof IntLinkedList) {
            IntLinkedList linkedList = (IntLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            IntIterator iterator1 = this.iterator();
            IntIterator iterator2 = linkedList.iterator();

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
        IntIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + iterator.next();
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        IntIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }
}
