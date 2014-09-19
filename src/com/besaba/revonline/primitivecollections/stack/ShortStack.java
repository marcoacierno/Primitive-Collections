package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.ShortConsumer;
import com.besaba.revonline.primitivecollections.iterables.ShortIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.ShortIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.ShortLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class ShortStack implements ShortIterable, Cloneable {
    private ShortLinkedList linkedList;

    private ShortStack(ShortLinkedList list) {
        this.linkedList = list;
    }

    private ShortStack() {
        linkedList = ShortLinkedList.empty();
    }

    public static ShortStack with(short... values) {
        return new ShortStack(ShortLinkedList.with(values));
    }

    public static ShortStack from(ShortStack stack) {
        return stack.clone();
    }

    public static ShortStack empty() {
        return new ShortStack();
    }

    public void push(short value) {
        linkedList.addFirst(value);
    }

    public short pop() {
        return linkedList.removeFirst();
    }

    public short peek() {
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

    public short[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(ShortConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public ShortIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public ShortStack clone() {
        ShortStack intStack;

        try {
            intStack = (ShortStack) super.clone();
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

        if (obj instanceof ShortStack) {
            ShortStack objList = (ShortStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
