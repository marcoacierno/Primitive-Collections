package com.besaba.revonline.primitivecollections.list.linkedlist;

/**
 * @author Marco
 * @since 1.0
 */
public class IntLinkedList {
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
        if (index < 0 || index > size) {
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

        return e.value;
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
}
