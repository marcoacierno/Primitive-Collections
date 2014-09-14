package com.besaba.revonline.primitivecollections.list;

import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IntArrayListTest {

    @Test
    public void testWithCapacity() throws Exception {
        int size = 20;

        ArrayList<Integer> arrayList = new ArrayList<Integer>(size);
        IntArrayList intArrayList = IntArrayList.withCapacity(size);

        assertEquals(arrayList.size(), intArrayList.size());
    }

    @Test
    public void testFrom() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        IntArrayList list = IntArrayList.withCapacity(1);

        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }

        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, list.asArray());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(10, IntArrayList.with(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(IntArrayList.withCapacity(0).isEmpty());
    }

    @Test
    public void testEmptyAfterRemove() throws Exception {
        IntArrayList listInt = IntArrayList.with(1);
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(IntArrayList.with(5, 10, 15, 20, 25, 30, 35, 40, 45, 50).contains(25));
    }

    @Test
    public void testIndexOf() throws Exception {
        assertEquals(1, IntArrayList.with(0, 1).indexOf(1));
    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testWith() throws Exception {
        IntArrayList list = IntArrayList.with(1, 2, 3, 4, 5, 6);
        int[] result = {1, 2, 3, 4, 5, 6};

        int prev = 0;
        IntIterator iterator = list.iterator();

        while (iterator.hasNext()) {
            assertEquals(result[prev++], iterator.next());
        }
    }

    @Test
    public void testEmpty() throws Exception {
        assertTrue(IntArrayList.empty().isEmpty());
    }

    @Test
    public void testRemove() throws Exception {
        int value = 10;

        IntArrayList listInt = IntArrayList.with(value);

        listInt.removeAt(0);

        assertTrue(listInt.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithInvalidIndex() throws Exception {
        int value = 10;

        IntArrayList listInt = IntArrayList.with(value);

        listInt.removeAt(1);

        assertTrue(listInt.isEmpty());
    }

    @Test
    public void testAsArray() throws Exception {
        int[] result = new int[] { 1, 2, 3, 4, 5 };
        IntArrayList list = IntArrayList.with(1, 2, 3, 4, 5);

        assertArrayEquals(result, list.asArray());
    }

    @Test
    public void testRemoveAt() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        IntArrayList list = IntArrayList.with(1, 2, 3, 4);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithAnInvalidIndex() throws Exception {
        IntArrayList list = IntArrayList.empty();

        assertEquals(1, list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithNegativeIndex() throws Exception {
        IntArrayList list = IntArrayList.empty();

        assertEquals(1, list.get(-1));
    }

    @Test
    public void testSizeProperty() throws Exception {
        IntArrayList list = IntArrayList.with(5, 3, 2);

        assertEquals(3, list.size());

        list.add(1);

        assertEquals(4, list.size());

        list.removeAt(0);
        list.removeAt(1);

        assertEquals(2, list.size());

        list.add(1);

        assertEquals(3, list.size());

        list.addAt(0, 5);

        assertEquals(4, list.size());
    }

    @Test
    public void testAddAllSourceCreatedUsingWithFactory() throws Exception {
        IntArrayList dest = IntArrayList.empty();
        dest.addAll(IntArrayList.with(1, 2, 3, 4));

        assertEquals(1, dest.get(0));
        assertEquals(2, dest.get(1));
        assertEquals(3, dest.get(2));
        assertEquals(4, dest.get(3));
    }

    @Test
    public void testAddAllAtWithSourceCreatedUsingWithFactoryWithStartIndex0() throws Exception {
        IntArrayList dest = IntArrayList.empty();
        dest.addAllAt(0, IntArrayList.with(1, 2, 3, 4));

        assertEquals(1, dest.get(0));
        assertEquals(2, dest.get(1));
        assertEquals(3, dest.get(2));
        assertEquals(4, dest.get(3));
    }

    @Test
    public void testAddAt() throws Exception {
        List<Integer> res = new ArrayList<Integer>(10);
        List<Integer> val = new ArrayList<Integer>();

        val.add(1);
        val.add(2);
        val.add(3);
        val.add(4);

        IntArrayList list = IntArrayList.with(1, 2, 3, 4);
        IntArrayList dest = IntArrayList.withCapacity(10);

        res.addAll(0, val);
        dest.addAllAt(0, list);

        int idx = 0;
        for(int v : res) {
            assertEquals(v, dest.get(idx++));
        }
    }

    @Test
    public void testResizeWithAddAll() throws Exception {
        IntArrayList dest = IntArrayList.empty();
        IntArrayList src = IntArrayList.empty();

        for (int i = 0; i < 10000; ++i) {
            src.add(i);
        }

        dest.addAll(src);

        assertEquals(10000, dest.size());

        for (int i = 0; i < 10000; ++i) {
            assertEquals(i, dest.get(i));
        }
    }

    @Test
    public void testAddAllAtWithSomeValuesAlreadyInsideTheCollection() throws Exception {
        IntArrayList dest = IntArrayList.with(1, 5, 10, 15, 20, 25, 30, 35);
        IntArrayList src = IntArrayList.with(6, 11, 16, 21, 26, 31, 36);

        dest.addAllAt(4, src);

        IntArrayList result = IntArrayList.with(1, 5, 10, 15, 6, 11, 16, 21, 26, 31, 36, 20, 25, 30, 35);

        assertEquals(result, dest);
    }

    @Test
    public void testEquals() throws Exception {
        IntArrayList list1 = IntArrayList.with(1, 5, 6, 10, 11, 50);
        IntArrayList list2 = IntArrayList.with(1, 5, 6, 10, 11, 50);

        assertEquals(list1, list2);
    }

    @Test
    public void testHashCode() throws Exception {
        int[] arr1 = new int[] { 1, 2, 3, 4, 5 };
        IntArrayList list = IntArrayList.with(1, 2, 3, 4, 5);

        assertEquals(Arrays.hashCode(arr1), list.hashCode());
    }

    @Test
    public void testCloneByAddingTwoValuesToTheOriginalList() throws Exception {
        IntArrayList list1 = IntArrayList.with(1, 2, 3, 4, 5);
        IntArrayList list2 = list1.clone();

        list1.add(1);
        list1.add(2);

        assertNotEquals(list1, list2);
    }

    @Test
    public void testCloneByAddingTwoValuesToTheClonedList() throws Exception {
        IntArrayList list1 = IntArrayList.with(1, 2, 3, 4, 5);
        IntArrayList list2 = list1.clone();

        list2.add(1);
        list2.add(2);

        assertNotEquals(list1, list2);
    }
}