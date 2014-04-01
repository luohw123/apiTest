package com.javaapi.test.testReflect;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class Reflect {
	/**
	 * 俩种方式获取类得实例 第一种
	 */
	@Test
	public void gClass() {
		try {
			Class<?> peopleClass = Class
					.forName("com.javaapi.test.testReflect.People");
			People people = (People) peopleClass.newInstance();
			PrintUtil.print(people);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 俩种方式获取类得实例 第二种
	 */
	@Test
	public void gClassConstruct() {
		try {
			Class<?> peopleClass = Class
					.forName("com.javaapi.test.testReflect.People");
			People people = (People) peopleClass.getConstructor(String.class)
					.newInstance("nihao");
			System.out.println(people.getName());
			PrintUtil.print(people);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
