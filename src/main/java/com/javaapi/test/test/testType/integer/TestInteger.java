package com.javaapi.test.test.testType.integer;

import org.junit.Test;

public class TestInteger {

	/**
	 * 都是对象,用==肯定不会相等
	 */
	@Test
	public void testInteger() {
		Integer aInteger = new Integer(10);
		Integer bInteger = new Integer(10);
		System.out.println(aInteger == bInteger);
		Integer abig = new Integer(129);
		Integer bbig = new Integer(129);
		System.out.println(abig == bbig);
		Integer a = Integer.valueOf(129);
		Integer b = Integer.valueOf(129);
		System.out.println(a == b);
	}
	@Test
	public void testInteger127() {
		Integer aInteger = 127;
		Integer bInteger = 127;
		System.out.println(aInteger == bInteger);
	}
	@Test
	public void testInteger128() {
		Integer aInteger = 128;
		Integer bInteger = 128;
		System.out.println(aInteger == bInteger);
	}
	/**
	 * 自动装箱是靠 Integer.valueOf()来实现,Integer.valueOf 之后是Integer对象,</br>
	 * http://blog.csdn.net/ma451152002/article/details/9076793
	 */
	@Test
	public void testInteger2() {
		int value = 10;
		Integer aInteger = value;
		Integer bInteger = value;
		System.out.println(aInteger == bInteger);
		System.out.println("---------------");
		Integer aValue = Integer.valueOf(value);
		Integer bValue = Integer.valueOf(value);
		System.out.println(aValue == bValue);
		System.out.println("---------------");
		
		
		
		
		
		
		
		
		
		int value2 = 129;
		Integer abig = value2;
		Integer bbig = value2;
		System.out.println(abig == bbig);
		System.out.println("---------------");
		Integer a = Integer.valueOf(value2);
		Integer b = Integer.valueOf(value2);
		System.out.println(a == b);
		System.out.println("---------------");
	}

	/**
	 * 因为在对a操作时(a=a+1或者a++)，a重新创建了一个对象，而b对应的还是缓存里的100，所以输出的结果为false
	 */
	@Test
	public void testInteger3() {
		Integer a = 100;
		Integer b = a;
		a = a + 1; // 或者a++;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a == b);
	}
}
