package com.javaapi.test.dataTrans.array;

import java.util.Arrays;

import org.junit.Test;

/**
 * http://www.cnblogs.com/chenssy/p/3466092.html 
 *不错
 *
 */
public class TestArray {

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
	@Test
	public void testSystemArrayCopyWrong() {
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
