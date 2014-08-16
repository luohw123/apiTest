package com.javaapi.test.map.hashmap;

import java.util.HashMap;

import org.junit.Test;

public class TestHashMap {

	@Test
	public void testHashMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("1", "2");
		System.out.println(map.get("1"));
	}

}
