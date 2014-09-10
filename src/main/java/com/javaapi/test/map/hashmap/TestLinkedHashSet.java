package com.javaapi.test.map.hashmap;

import java.util.Iterator;
import java.util.LinkedHashSet;

import org.junit.Test;

/**
 * http://www.cnblogs.com/Terry-greener/archive/2011/12/02/2271707.html
 * 
 */
public class TestLinkedHashSet {

	@Test
	public void testLinkedHashSet() {
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		set.add(2);
		set.add(1);
		set.add(3);
		set.add(5);
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Integer integer = (Integer) it.next();
			System.out.println(integer);
		}
	}
}
