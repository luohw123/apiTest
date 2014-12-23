package com.javaapi.test.map.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Test
    public void testHashMapRemove() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
        map1.put("2", 22);
        map1.put("3", 3);
        map1.put("4", 4);
        map.putAll(map1);
        map.remove("2");
        System.out.println(map);
    }
    @Test
    public void testHashMapPutAll() {
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
    @Test
    public void testHashMapInit() {
        List<String> input =new ArrayList<>();
        input.add("nihao");
        input.add("nihao");
        input.add("nihao");
        input.add("nihao");
        input.add("nihao");
        input.add("nihao");
        input.add("nihao");
        int mapSize = (int)Math.ceil(input.size() / 0.7);
        
        Map<String,String> map = new HashMap<String, String>(mapSize); 
        for(String f: input)
        {
            map.put("key", f);
        }
    }
}
