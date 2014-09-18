package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.FloatConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.FloatIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.FloatIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class FloatLinkedList implements FloatIterable, Cloneable {
    private transient Node header = new Node((float)0.0f, null, null);
    private transient int size;

    private FloatLinkedList() {
        header.next = header.previous = header;
    }

    public static FloatLinkedList empty() {
        return new FloatLinkedList();
    }

    public static FloatLinkedList with(float... values) {
        FloatLinkedList list = new FloatLinkedList();

        for(float value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(float value) {
        addBefore(value, header);
    }

    public void addFirst(float value) {
        addBefore(value, header.next);
    }

    public float get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public float getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public float getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public float set(int index, float value) {
        Node prev = lookupNode(index);

        float oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public float removeAt(int index) {
        return remove(lookupNode(index));
    }

    public float removeFirst() {
        return remove(header.next);
    }

    public float removeLast() {
        return remove(header.previous);
    }

    public int indexOf(float value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(float value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public float[] asArray() {
        final float[] array = new float[size];

        forEach(new FloatConsumer() {
            private int index = 0;

            @Override
            public void accept(float value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(FloatConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(float value, Node before) {
        Node newNode = new Node(value, before, before.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    private float remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        float oldValue = node.value;

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
    public FloatIterator iterator() {
        return new FloatIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public float next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private float value;

        private Node(float value, Node next, Node previous) {
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

        if (obj instanceof FloatLinkedList) {
            FloatLinkedList linkedList = (FloatLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            FloatIterator iterator1 = this.iterator();
            FloatIterator iterator2 = linkedList.iterator();

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
        FloatIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        FloatIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    public FloatLinkedList clone() {
        final FloatLinkedList linkedList;

        try {
            linkedList = (FloatLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((float)0.0f, null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new FloatConsumer() {
            @Override
            public void accept(float value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}