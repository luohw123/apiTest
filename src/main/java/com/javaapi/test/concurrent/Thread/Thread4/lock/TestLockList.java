package com.javaapi.test.concurrent.Thread.Thread4.lock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 给list加锁,然后当前获得list锁得线程还是可以将元素放入list中的
 * 
 */
public class TestLockList {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		addlist(list);
		System.out.println(list);
	}

	public void addlist(List<Integer> list) {
		synchronized (list) {
			list.add(3);
		}
	}
}
