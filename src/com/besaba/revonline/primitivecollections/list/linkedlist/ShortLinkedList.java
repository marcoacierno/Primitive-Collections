package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.ShortConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.ShortIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.ShortIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class ShortLinkedList implements ShortIterable, Cloneable {
    private transient Node header = new Node((short)0, null, null);
    private transient int size;

    private ShortLinkedList() {
        header.next = header.previous = header;
    }

    public static ShortLinkedList empty() {
        return new ShortLinkedList();
    }

    public static ShortLinkedList with(short... values) {
        ShortLinkedList list = new ShortLinkedList();

        for(short value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(short value) {
        addBefore(value, header);
    }

    public void addFirst(short value) {
        addBefore(value, header.next);
    }

    public short get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public short getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public short getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public short set(int index, short value) {
        Node prev = lookupNode(index);

        short oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public short removeAt(int index) {
        return remove(lookupNode(index));
    }

    public short removeFirst() {
        return remove(header.next);
    }

    public short removeLast() {
        return remove(header.previous);
    }

    public int indexOf(short value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(short value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public short[] asArray() {
        final short[] array = new short[size];

        forEach(new ShortConsumer() {
            private int index = 0;

            @Override
            public void accept(short value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(ShortConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(short value, Node before) {
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

        header = new Node((short)0, null, null);
        header.next = header.previous = header;
        size = 0;
    }

    private short remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        short oldValue = node.value;

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
    public ShortIterator iterator() {
        return new ShortIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public short next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private short value;

        private Node(short value, Node next, Node previous) {
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

        if (obj instanceof ShortLinkedList) {
            ShortLinkedList linkedList = (ShortLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            ShortIterator iterator1 = this.iterator();
            ShortIterator iterator2 = linkedList.iterator();

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
        ShortIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        ShortIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    public ShortLinkedList clone() {
        final ShortLinkedList linkedList;

        try {
            linkedList = (ShortLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((short)0, null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new ShortConsumer() {
            @Override
            public void accept(short value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}
