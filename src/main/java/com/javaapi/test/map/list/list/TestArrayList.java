package com.javaapi.test.map.list.list;

import java.util.ArrayList;

import org.junit.Test;

public class TestArrayList {
    /**
     * 
     *    原样添加
     */
    @Test
    public void testListAddAll() {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(13);
        list1.add(23);
        list1.add(33);
        list1.add(43);
        list1.add(53);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(14);
        list2.add(24);
        list2.add(34);
        list2.add(43);
        list2.add(53);
        // 原样添加
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * removeAll,addAll 组合后达到 添加不同得
     * @create_time 2014年9月18日 下午5:31:42 
     */
    @Test
    public void testListMerge() {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(13);
        list1.add(23);
        list1.add(33);
        list1.add(43);
        list1.add(53);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(14);
        list2.add(24);
        list2.add(34);
        list2.add(43);
        list2.add(53);
        // 有相同得则移除
        list1.removeAll(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * 找出相同得值
     * @create_time 2014年9月18日 下午5:31:13 
     */
    @Test
    public void testListRetain() {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(13);
        list1.add(23);
        list1.add(33);
        list1.add(43);
        list1.add(53);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(14);
        list2.add(24);
        list2.add(34);
        list2.add(43);
        list2.add(53);
        list1.retainAll(list2);
        System.out.println(list1);

    }

}
