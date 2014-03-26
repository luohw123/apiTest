package com.javaapi.test.map.commons;

import java.util.HashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.Test;

public class TestApacheCommmon {
	/**
	 * maputils 测试默认值
	 */
	@Test
	public void testMapUtils() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 1);
		map.put("c", 1);
		System.out.println(MapUtils.getInteger(map, "c", 12));
		System.out.println(MapUtils.getInteger(map, "d", 12));
	}

	@Test
	public void testMap() {
		MultiValueMap<String, Integer> map = new MultiValueMap<String, Integer>();
		map.put("a", 11);
		map.put("a", 12);
		map.put("b", 13);
		map.put("b", 14);
		System.out.println(map.getCollection("a"));
	}
}
