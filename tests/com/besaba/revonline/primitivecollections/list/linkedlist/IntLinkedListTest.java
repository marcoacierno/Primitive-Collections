package com.besaba.revonline.primitivecollections.list.linkedlist;

import com.besaba.revonline.primitivecollections.function.IntConsumer;
import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IntLinkedListTest {

    @Test
    public void testWith() throws Exception {
        IntLinkedList dest = IntLinkedList.with(5, 10, 25, 30, 35, 40);
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5, 10, 25, 30, 35, 40));

        assertEquals(dest.get(0), (int)list.get(0));
        assertEquals(dest.get(1), (int)list.get(1));
        assertEquals(dest.get(2), (int)list.get(2));
        assertEquals(dest.get(3), (int)list.get(3));
        assertEquals(dest.get(4), (int)list.get(4));
        assertEquals(dest.get(5), (int)list.get(5));

        assertEquals(dest.size(), list.size());
    }

    @Test
    public void testAdd() throws Exception {
        IntLinkedList list = IntLinkedList.empty();
        list.add(0);
        list.add(1);

        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.add(0);
        list2.add(1);

        assertEquals(list.get(0), (int)list2.get(0));
        assertEquals(list.get(1), (int)list2.get(1));
    }

    @Test
    public void testEmpty() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInAnEmptyLinkedList() throws Exception {
        IntLinkedList list = IntLinkedList.empty();
        list.get(0);
    }

    @Test
    public void testSize() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 2, 3, 4, 5, -1, -2, -3, -4);
        assertEquals(9, list.size());
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testRemoveAt() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        list.removeAt(0);

        IntLinkedList result = IntLinkedList.with(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        assertEquals(result, list);
    }

    @Test
    public void testIterator() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 2, 3, 4, 5, 6);
        IntIterator iterator = list.iterator();

        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals(4, iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals(5, iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals(6, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testGetFirst() throws Exception {
        IntLinkedList list = IntLinkedList.with(5, 4, 3, 2, 1);
        assertEquals(5, list.getFirst());
    }

    @Test
    public void testGetLast() throws Exception {
        IntLinkedList list = IntLinkedList.with(5, 4, 3, 2, 1);
        assertEquals(1, list.getLast());
    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testRemoveFirst() throws Exception {
        IntLinkedList list = IntLinkedList.with(5, 4, 3, 2, 1);

        assertEquals(5, list.removeFirst());
        assertEquals(4, list.size());
    }

    @Test
    public void testRemoveLast() throws Exception {
        IntLinkedList list = IntLinkedList.with(5, 4, 3, 2, 1);

        assertEquals(1, list.removeLast());
        assertEquals(4, list.size());
    }

    @Test
    public void testRemoveFirstAndCompare() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 2, 3, 4, 5, 6, 7);
        list.removeFirst();
        list.removeFirst();

        assertEquals(IntLinkedList.with(3, 4, 5, 6, 7), list);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstWithAnEmptyList() throws Exception {
        IntLinkedList list = IntLinkedList.empty();
        list.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastWithAnEmptyList() throws Exception {
        IntLinkedList list = IntLinkedList.empty();
        list.removeLast();
    }


    @Test
    public void testSet() throws Exception {
        IntLinkedList list = IntLinkedList.with(-1, -2, -3, -4);
        list.set(1, 2);

        assertEquals(2, list.get(1));
    }

    @Test
    public void testIndexOf() throws Exception {
        IntLinkedList list = IntLinkedList.with(-1, -2, -3, -4);

        assertEquals(1, list.indexOf(-2));
    }

    @Test
    public void testIndexOfWithWrongValue() throws Exception {
        IntLinkedList list = IntLinkedList.with(-1, -2, -3, -4);

        assertEquals(-1, list.indexOf(6));
    }

    @Test
    public void testIndexOfWithEmptyList() throws Exception {
        IntLinkedList list = IntLinkedList.empty();

        assertEquals(-1, list.indexOf(6));
    }

    @Test
    public void testLastIndexOf() throws Exception {
        IntLinkedList list = IntLinkedList.with(-1, -3, -3, -4);

        assertEquals(2, list.lastIndexOf(-3));
    }

    @Test
    public void testLastIndexOfWithTwoFarValues() throws Exception {
        IntLinkedList list = IntLinkedList.with(-1, -3, -3, -4, -3, -2, -1, -4, -4, -4, -3);

        assertEquals(10, list.lastIndexOf(-3));
    }

    @Test
    public void testLastIndexOfWithAnEmptyList() throws Exception {
        IntLinkedList list = IntLinkedList.empty();

        assertEquals(-1, list.lastIndexOf(-3));
    }

    @Test
    public void testForEach() throws Exception {
        IntLinkedList start = IntLinkedList.with(1, 2, 3, 4, 5);
        IntLinkedList result = IntLinkedList.with(2, 4, 6, 8, 10);

        final IntLinkedList intermedia = IntLinkedList.empty();
        start.forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                intermedia.add(value * 2);
            }
        });

        assertEquals(result, intermedia);
    }

    @Test
    public void testForEachWithAnEmptyList() throws Exception {
        IntLinkedList start = IntLinkedList.empty();
        IntLinkedList result = IntLinkedList.empty();

        final IntLinkedList intermedia = IntLinkedList.empty();
        start.forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                intermedia.add(value * 2);
            }
        });

        assertEquals(result, intermedia);
    }

    @Test
    public void testAsArray() throws Exception {
        IntLinkedList linkedList = IntLinkedList.with(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, linkedList.asArray());
    }

    @Test
    public void testClone() throws Exception {
        IntLinkedList original = IntLinkedList.with(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntLinkedList clone = original.clone();

        assertEquals(original, clone);

        clone.removeFirst();

        assertNotEquals(original, clone);
    }

    @Test
    public void testAddFirst() throws Exception {
        IntLinkedList list = IntLinkedList.with(1, 2, 3);
        list.addFirst(5);

        assertArrayEquals(new int[] {5, 1, 2, 3}, list.asArray());
    }

    @Test
    public void testAddFirstInAnEmptyCollection() throws Exception {
        IntLinkedList list = IntLinkedList.empty();
        list.addFirst(3);

        assertArrayEquals(new int[] {3}, list.asArray());
    }
}