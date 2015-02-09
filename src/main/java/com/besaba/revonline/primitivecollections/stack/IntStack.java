package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.IntConsumer;
import com.besaba.revonline.primitivecollections.iterables.IntIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.IntLinkedList;

import java.io.Serializable;

/**
 * @author Marco
 * @since 1.0
 */
public class IntStack implements IntIterable, Cloneable, Serializable {
    private IntLinkedList linkedList;

    private IntStack(IntLinkedList list) {
        this.linkedList = list;
    }

    private IntStack() {
        linkedList = IntLinkedList.empty();
    }

    public static IntStack with(int... values) {
        return new IntStack(IntLinkedList.with(values));
    }

    public static IntStack from(IntStack stack) {
        return stack.clone();
    }

    public static IntStack empty() {
        return new IntStack();
    }

    public void push(int value) {
        linkedList.addFirst(value);
    }

    public int pop() {
        return linkedList.removeFirst();
    }

    public int peek() {
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

    public int[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(IntConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public IntIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public IntStack clone() {
        IntStack intStack;

        try {
            intStack = (IntStack) super.clone();
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

        if (obj instanceof IntStack) {
            IntStack objList = (IntStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
