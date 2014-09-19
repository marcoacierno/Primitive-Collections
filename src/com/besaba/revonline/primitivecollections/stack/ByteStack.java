package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.ByteConsumer;
import com.besaba.revonline.primitivecollections.iterables.ByteIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.ByteIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.ByteLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class ByteStack implements ByteIterable, Cloneable {
    private ByteLinkedList linkedList;

    private ByteStack(ByteLinkedList list) {
        this.linkedList = list;
    }

    private ByteStack() {
        linkedList = ByteLinkedList.empty();
    }

    public static ByteStack with(byte... values) {
        return new ByteStack(ByteLinkedList.with(values));
    }

    public static ByteStack from(ByteStack stack) {
        return stack.clone();
    }

    public static ByteStack empty() {
        return new ByteStack();
    }

    public void push(byte value) {
        linkedList.addFirst(value);
    }

    public byte pop() {
        return linkedList.removeFirst();
    }

    public byte peek() {
        return linkedList.getFirst();
    }

    public int size() {
        return linkedList.size();
    }

    public void clear() {
        linkedList.clear();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public byte[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(ByteConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public ByteIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public ByteStack clone() {
        ByteStack intStack;

        try {
            intStack = (ByteStack) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        intStack.linkedList = linkedList.clone();
        return intStack;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj instanceof ByteStack) {
            ByteStack objList = (ByteStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
