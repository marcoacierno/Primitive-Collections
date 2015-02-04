package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.FloatConsumer;
import com.besaba.revonline.primitivecollections.iterables.FloatIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.FloatIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.FloatLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class FloatStack implements FloatIterable, Cloneable {
    private FloatLinkedList linkedList;

    private FloatStack(FloatLinkedList list) {
        this.linkedList = list;
    }

    private FloatStack() {
        linkedList = FloatLinkedList.empty();
    }

    public static FloatStack with(float... values) {
        return new FloatStack(FloatLinkedList.with(values));
    }

    public static FloatStack from(FloatStack stack) {
        return stack.clone();
    }

    public static FloatStack empty() {
        return new FloatStack();
    }

    public void push(float value) {
        linkedList.addFirst(value);
    }

    public float pop() {
        return linkedList.removeFirst();
    }

    public float peek() {
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

    public float[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(FloatConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public FloatIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public FloatStack clone() {
        FloatStack intStack;

        try {
            intStack = (FloatStack) super.clone();
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

        if (obj instanceof FloatStack) {
            FloatStack objList = (FloatStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
