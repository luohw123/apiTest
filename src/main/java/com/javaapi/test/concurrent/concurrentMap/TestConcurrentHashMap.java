package com.javaapi.test.concurrent.concurrentMap;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestConcurrentHashMap {
	@Test
	public void testName() throws Exception {
		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
		Object putIfAbsent = concurrentHashMap.putIfAbsent("1", "1");
		System.err.println(putIfAbsent);
		Object putIfAbsent_tmp= concurrentHashMap.putIfAbsent("1", "1");
		System.err.println(putIfAbsent_tmp);
	}

}
