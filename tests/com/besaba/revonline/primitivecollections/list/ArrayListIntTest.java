package com.besaba.revonline.primitivecollections.list;

import com.besaba.revonline.primitivecollections.iterables.iterators.IntIterator;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArrayListIntTest {

    @Test
    public void testWithCapacity() throws Exception {
        int size = 20;

        ArrayList<Integer> arrayList = new ArrayList<Integer>(size);
        ArrayListInt arrayListInt = ArrayListInt.withCapacity(size);

        assertEquals(arrayList.size(), arrayListInt.size());
    }

    @Test
    public void testFrom() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testContains() throws Exception {

    }

    @Test
    public void testIndexOf() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testWith() throws Exception {
        ArrayListInt list = ArrayListInt.with(1, 2, 3, 4, 5, 6);
        int[] result = {1, 2, 3, 4, 5, 6};

        int prev = 0;
        IntIterator iterator = list.iterator();

        while (iterator.hasNext()) {
            assertEquals(result[prev++], iterator.next());
        }
    }
}