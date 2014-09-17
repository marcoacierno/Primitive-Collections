package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.CharConsumer;
import com.besaba.revonline.primitivecollections.internal.Utils;
import com.besaba.revonline.primitivecollections.iterables.CharIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.CharIterator;

import java.util.NoSuchElementException;

/**
 * @author Marco
 * @since 1.0
 */
public class CharLinkedList implements CharIterable, Cloneable {
    private transient Node header = new Node((char)'\u0000', null, null);
    private transient int size;

    private CharLinkedList() {
        header.next = header.previous = header;
    }

    public static CharLinkedList empty() {
        return new CharLinkedList();
    }

    public static CharLinkedList with(char... values) {
        CharLinkedList list = new CharLinkedList();

        for(char value : values) {
            list.add(value);
        }

        return list;
    }

    public void add(char value) {
        addBefore(value, header);
    }

    public void addFirst(char value) {
        addBefore(value, header.next);
    }

    public char get(int index) {
        Node e = lookupNode(index);

        return e.value;
    }

    public char getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.value;
    }

    public char getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.value;
    }

    public char set(int index, char value) {
        Node prev = lookupNode(index);

        char oldValue = prev.value;
        prev.value = value;

        return oldValue;
    }

    public char removeAt(int index) {
        return remove(lookupNode(index));
    }

    public char removeFirst() {
        return remove(header.next);
    }

    public char removeLast() {
        return remove(header.previous);
    }

    public int indexOf(char value) {
        int index = 0;

        for (Node node = header.next; node != header; node = node.next) {
            if (node.value == value) {
                return index;
            }

            ++index;
        }

        return -1;
    }

    public int lastIndexOf(char value) {
        int index = size - 1;

        for (Node node = header.previous; node != header; node = node.previous) {
            if (node.value == value) {
                return index;
            }

            --index;
        }

        return -1;
    }

    public char[] asArray() {
        final char[] array = new char[size];

        forEach(new CharConsumer() {
            private int index = 0;

            @Override
            public void accept(char value) {
                array[index++] = value;
            }
        });

        return array;
    }

    public void forEach(CharConsumer consumer) {
        for (Node node = header.next; node != header; node = node.next) {
            consumer.accept(node.value);
        }
    }

    private void addBefore(char value, Node before) {
        Node newNode = new Node(value, before, before.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    public int size() {
        return size;
    }

    private char remove(Node node) {
        if (node == header) {
            throw new NoSuchElementException();
        }

        char oldValue = node.value;

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
    public CharIterator iterator() {
        return new CharIterator() {
            private Node current = header;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer != size;
            }

            @Override
            public char next() {
                ++pointer;
                current = current.next;
                return current.value;
            }
        };
    }

    private static class Node {
        private Node next;
        private Node previous;
        private char value;

        private Node(char value, Node next, Node previous) {
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

        if (obj instanceof CharLinkedList) {
            CharLinkedList linkedList = (CharLinkedList) obj;

            if (linkedList.size != size) {
                return false;
            }

            CharIterator iterator1 = this.iterator();
            CharIterator iterator2 = linkedList.iterator();

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
        CharIterator iterator = iterator();

        while (iterator.hasNext()) {
            hashCode = 31 * hashCode + Utils.hashCode(iterator.next());
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size: ").append(size).append(", Values: ");

        CharIterator iterator = iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).append(", ");
        }

        builder.setLength(builder.length() - 2);

        return builder.toString();
    }

    @Override
    protected CharLinkedList clone() {
        final CharLinkedList linkedList;

        try {
            linkedList = (CharLinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        linkedList.header = new Node((char)'\u0000', null, null);
        linkedList.header.next = linkedList.header.previous = linkedList.header;
        linkedList.size = 0;

        forEach(new CharConsumer() {
            @Override
            public void accept(char value) {
                linkedList.add(value);
            }
        });

        return linkedList;
    }
}
