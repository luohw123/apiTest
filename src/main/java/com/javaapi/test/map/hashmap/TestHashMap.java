package com.javaapi.test.map.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * http://www.cnblogs.com/hubingxu/archive/2012/02/21/2361281.html</br>
 * 一般情况下，我们用的最多的是HashMap,在Map 中插入、删除和定位元素，HashMap
 * 是最好的选择。但如果您要按自然顺序或自定义顺序遍历键，那么TreeMap会更好。如果需要输出的顺序和输入的相同,那么用LinkedHashMap
 * 可以实现,它还可以按读取顺序来排列.
 * 
 */
public class TestHashMap {

	@Test
	public void testHashMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("1", "2");
		System.out.println(map.get("1"));
	}

    @Test
    public void testHashMapMerge() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
        map1.put("2", 22);
        map1.put("3", 3);
        map1.put("4", 4);
        map.putAll(map1);
        System.out.println(map);
    }

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
        // 有相同得则移除
        list1.retainAll(list2);
        System.out.println(list1);

    }

}
