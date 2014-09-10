package com.javaapi.test.map.hashmap;

import java.util.HashMap;

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

}
