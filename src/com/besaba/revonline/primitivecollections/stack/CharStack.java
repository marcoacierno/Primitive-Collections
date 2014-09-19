package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.CharConsumer;
import com.besaba.revonline.primitivecollections.iterables.CharIterable;
import com.besaba.revonline.primitivecollections.iterables.iterators.CharIterator;
import com.besaba.revonline.primitivecollections.list.linkedlist.CharLinkedList;

/**
 * @author Marco
 * @since 1.0
 */
public class CharStack implements CharIterable, Cloneable {
    private CharLinkedList linkedList;

    private CharStack(CharLinkedList list) {
        this.linkedList = list;
    }

    private CharStack() {
        linkedList = CharLinkedList.empty();
    }

    public static CharStack with(char... values) {
        return new CharStack(CharLinkedList.with(values));
    }

    public static CharStack from(CharStack stack) {
        return stack.clone();
    }

    public static CharStack empty() {
        return new CharStack();
    }

    public void push(char value) {
        linkedList.addFirst(value);
    }

    public char pop() {
        return linkedList.removeFirst();
    }

    public char peek() {
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

    public char[] asArray() {
        return linkedList.asArray();
    }

    public void forEach(CharConsumer consumer) {
        linkedList.forEach(consumer);
    }

    @Override
    public CharIterator iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public CharStack clone() {
        CharStack intStack;

        try {
            intStack = (CharStack) super.clone();
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

        if (obj instanceof CharStack) {
            CharStack objList = (CharStack) obj;
            return objList.linkedList.equals(this.linkedList);
        }

        return false;
    }
}
