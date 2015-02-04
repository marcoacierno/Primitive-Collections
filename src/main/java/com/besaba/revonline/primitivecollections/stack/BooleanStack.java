package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.BooleanConsumer;
import com.besaba.revonline.primitivecollections.iterables.BooleanIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.BooleanIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.BooleanLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class BooleanStack implements BooleanIterable, Cloneable {
    private BooleanLinkedList linkedList;

    private BooleanStack(BooleanLinkedList list) {
        this.linkedList = list;
    }

    private BooleanStack() {
        linkedList = BooleanLinkedList.empty();
    }

    public static BooleanStack with(boolean... values) {
        return new BooleanStack(BooleanLinkedList.with(values));
    }

    public static BooleanStack from(BooleanStack stack) {
        return stack.clone();
    }

    public static BooleanStack empty() {
        return new BooleanStack();
    }

    public void push(boolean value) {
        linkedList.addFirst(value);
    }

    public boolean pop() {
        return linkedList.removeFirst();
    }

    public boolean peek() {
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

    public boolean[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(BooleanConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public BooleanIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public BooleanStack clone() {
        BooleanStack intStack;

        try {
            intStack = (BooleanStack) super.clone();
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

        if (obj instanceof BooleanStack) {
            BooleanStack objList = (BooleanStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
