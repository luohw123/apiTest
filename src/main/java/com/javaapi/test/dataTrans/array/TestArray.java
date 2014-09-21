package com.javaapi.test.dataTrans.array;

import java.util.Arrays;

import org.junit.Test;

/**
 * http://www.cnblogs.com/chenssy/p/3466092.html 
 *不错
 *  所以在性能要求较高的场景中请优先考虑数组。</br>
 *  java 中把数组也当做对象来用
 */
public class TestArray {

	
	@Test
	public void test() {
		int[] a = new int[5];
		System.out.println(a.toString());
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * System.arraycopy 不会有任何附加功能.所以有可能产生越界等异常
	 */
	@Test
	public void testSystemArrayCopy() {
		int[] ids = { 1, 2, 3, 4, 5 };
		int[] id2 = { 2, 3, 4, 5, 6, 8 };
		System.arraycopy(ids, 0, id2,0, ids.length);
		System.out.println(Arrays.toString(ids));
		System.out.println(Arrays.toString(id2));
	}
	/**
	 * 注意 目的数组得长度不要越界
	 */
	@Test
	public void testSystemArray() {
		int[] ids = { 1, 2, 3, 4, 5,9 };
		int[] id2 = { 2, 3, 4, 5, 6, 8 };
		System.arraycopy(ids, 0, id2,0, ids.length);
		System.out.println(Arrays.toString(ids));
		System.out.println(Arrays.toString(id2));
	}
	@Test
	public void testArraysCopy() {
		int[] ids = { 1, 2, 3, 4, 5,9 };
		int[] afterCopy = Arrays.copyOf(ids, 20);
		System.out.println(Arrays.toString(afterCopy));
	}
}
