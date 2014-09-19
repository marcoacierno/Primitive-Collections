package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.DoubleConsumer;
import com.besaba.revonline.primitivecollections.iterables.DoubleIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.DoubleIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.DoubleLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class DoubleStack implements DoubleIterable, Cloneable {
    private DoubleLinkedList linkedList;

    private DoubleStack(DoubleLinkedList list) {
        this.linkedList = list;
    }

    private DoubleStack() {
        linkedList = DoubleLinkedList.empty();
    }

    public static DoubleStack with(double... values) {
        return new DoubleStack(DoubleLinkedList.with(values));
    }

    public static DoubleStack from(DoubleStack stack) {
        return stack.clone();
    }

    public static DoubleStack empty() {
        return new DoubleStack();
    }

    public void push(double value) {
        linkedList.addFirst(value);
    }

    public double pop() {
        return linkedList.removeFirst();
    }

    public double peek() {
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

    public double[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(DoubleConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public DoubleIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public DoubleStack clone() {
        DoubleStack intStack;

        try {
            intStack = (DoubleStack) super.clone();
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

        if (obj instanceof DoubleStack) {
            DoubleStack objList = (DoubleStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
