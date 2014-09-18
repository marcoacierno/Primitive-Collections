package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.iterables.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.IntLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class IntStackLinkedList implements IntIterable {
    private final IntLinkedList linkedList;

    private IntStackLinkedList(IntLinkedList list) {
        this.linkedList = list;
    }

    private IntStackLinkedList() {
        linkedList = IntLinkedList.empty();
    }

    public static IntStackLinkedList with(int... values) {
        return new IntStackLinkedList(IntLinkedList.with(values));
    }

    public static IntStackLinkedList empty() {
        return new IntStackLinkedList();
    }

    public void push(int value) {
        linkedList.addFirst(value);
    }

    public int pop() {
        return linkedList.removeFirst();
    }

    public int head() {
        return linkedList.getFirst();
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public IntIterator iterator() {
        return linkedList.iterator();
    }
}
