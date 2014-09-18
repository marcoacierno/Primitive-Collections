package com.besaba.revonline.primitivecollections.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class IntStackLinkedListTest {

    @Test
    public void testWith() throws Exception {
        IntStackLinkedList stack = IntStackLinkedList.empty();
        Deque<Integer> jdkStack = new LinkedList<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        jdkStack.push(1);
        jdkStack.push(2);
        jdkStack.push(3);
        jdkStack.push(4);
        jdkStack.push(5);

        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
    }

    @Test
    public void testEmpty() throws Exception {
        IntStackLinkedList stack = IntStackLinkedList.empty();
        Deque<Integer> jdkStack = new LinkedList<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        jdkStack.push(1);
        jdkStack.push(2);
        jdkStack.push(3);
        jdkStack.push(4);
        jdkStack.push(5);

        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());
        assertEquals(stack.pop(), (int)jdkStack.poll());

        assertTrue(stack.isEmpty());
        assertTrue(jdkStack.isEmpty());
    }

    @Test
    public void testPush() throws Exception {
        IntStackLinkedList stack = IntStackLinkedList.empty();

        stack.push(1);
        stack.push(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        assertEquals(50, stack.pop());
        assertEquals(40, stack.pop());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testPop() throws Exception {

    }

    @Test
    public void testHead() throws Exception {
        IntStackLinkedList stack = IntStackLinkedList.empty();

        stack.push(5);
        assertEquals(5, stack.peek());

        stack.push(3);
        assertEquals(3, stack.peek());

        stack.push(1);
        assertEquals(1, stack.peek());
    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testClone() throws Exception {
        IntStackLinkedList stack = IntStackLinkedList.empty();
        IntStackLinkedList stack2 = stack.clone();

        stack.push(5);
        assertEquals(5, stack.peek());

        stack.push(3);
        assertEquals(3, stack.peek());

        stack.push(1);
        assertEquals(1, stack.peek());

        assertNotEquals(stack, stack2);
    }

    @Test
    public void testPeek() throws Exception {

    }

    @Test
    public void testAsArray() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(
                IntStackLinkedList.with(1, 2, 3, 4, 5, 6),
                IntStackLinkedList.with(1, 2, 3, 4, 5, 6)
        );
    }
}