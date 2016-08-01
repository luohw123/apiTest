package com.javaapi.test.testUtil.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 15/9/6.
 */
public class TestJdkCollections {
    @Test
    public void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(8);
        list.add(3);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("list = " + list);
    }

    @Test
    public void testClear() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(8);
        list.add(3);
        list.clear();
        list.add(5);
        System.out.println(list);

    }
}
