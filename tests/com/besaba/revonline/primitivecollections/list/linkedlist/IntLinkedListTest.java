package com.besaba.revonline.primitivecollections.list.linkedlist;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

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
}