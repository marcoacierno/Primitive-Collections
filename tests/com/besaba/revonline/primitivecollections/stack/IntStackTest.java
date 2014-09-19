package com.besaba.revonline.primitivecollections.stack;

import com.besaba.revonline.primitivecollections.function.IntConsumer;
import com.besaba.revonline.primitivecollections.list.linkedlist.IntLinkedList;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class IntStackTest {

    @Test
    public void testWith() throws Exception {
        IntStack stack = IntStack.empty();
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
        IntStack stack = IntStack.empty();
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
        IntStack stack = IntStack.empty();

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
        IntStack stack = IntStack.with(1, 50, 505, 1010, 2020, 3030, 4040, 5050);

        stack.push(32);
        stack.push(31);
        stack.push(65);
        stack.push(1478);
        stack.push(1567);
        stack.push(11234);
        stack.push(1125);

        assertEquals(1125, stack.pop());
        assertEquals(11234, stack.pop());
        assertEquals(1567, stack.pop());

        assertFalse(stack.isEmpty());
    }

    @Test
    public void testHead() throws Exception {
        IntStack stack = IntStack.empty();

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
        IntStack stack = IntStack.empty();
        IntStack stack2 = stack.clone();

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
        IntStack stack = IntStack.empty();

        for (int i = 0; i < 25; ++i) {
            stack.push(i);
        }

        assertEquals(24, stack.peek());
        assertEquals(24, stack.pop());
        assertEquals(23, stack.peek());
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
                IntStack.with(1, 2, 3, 4, 5, 6),
                IntStack.with(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    public void testForEach() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 101, 1010, 101010, 1010101010, 1010101);
        final int[] values = new int[] {1, 101, 1010, 101010, 1010101010, 1010101};

        list.forEach(new IntConsumer() {
            int p = 0;

            @Override
            public void accept(int value) {
                assertEquals(values[p++], value);
            }
        });
    }
}