package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.ByteConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.ByteIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.ByteIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class ByteLinkedList implements ByteIterable, Cloneable {
    private transient Node header = new Node((byte)0, null, null);
    private transient int size;

    private ByteLinkedList() {
        header.next = header.previous = header;
    }

    public static ByteLinkedList empty() {
        return new ByteLinkedList();
    }

    public static ByteLinkedList with(byte... values) {
        ByteLinkedList list = new ByteLinkedList();

        for(byte value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(byte value) {
        addBefore(value, header);
    }

    public void addFirst(byte value) {
        addBefore(value, header.next);
    }

    public byte get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public byte getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public byte getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public byte set(int index, byte value) {
        Node prev = lookupNode(index);

        byte oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public byte removeAt(int index) {
        return remove(lookupNode(index));
    }

    public byte removeFirst() {
        return remove(header.next);
    }

    public byte removeLast() {
        return remove(header.previous);
    }

    public int indexOf(byte value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(byte value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public byte[] asArray() {
        final byte[] array = new byte[size];

        forEach(new ByteConsumer() {
            private int index = 0;

            @Override
            public void accept(byte value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(ByteConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(byte value, Node before) {
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

        header = new Node((byte)0, null, null);
        header.next = header.previous = header;
        size = 0;
    }

    private byte remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        byte oldValue = node.value;

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
    public ByteIterator iterator() {
        return new ByteIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public byte next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private byte value;

        private Node(byte value, Node next, Node previous) {
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

        if (obj instanceof ByteLinkedList) {
            ByteLinkedList linkedList = (ByteLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            ByteIterator iterator1 = this.iterator();
            ByteIterator iterator2 = linkedList.iterator();

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
        ByteIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        ByteIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    public ByteLinkedList clone() {
        final ByteLinkedList linkedList;

        try {
            linkedList = (ByteLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((byte)0, null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new ByteConsumer() {
            @Override
            public void accept(byte value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}
