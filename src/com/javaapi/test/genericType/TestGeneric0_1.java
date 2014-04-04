package com.javaapi.test.genericType;

import java.util.Collection;
import java.util.List;

/**
 * 泛型方法
 * 
 */
public class TestGeneric0_1 {
	public <T> void test(T t) {
		System.out.println(t.getClass().getName());
	}
	/**
	 * 
	 * 既使用范型参数，又使用范型方法
	 */
	public static <T>  void copy(List<T> dest, List<? extends T> src){
		//...
		
	}
	public static void main(String[] args) {
		String t = "aa";
		Integer i = 0;
		Object o = new Object();
		new TestGeneric0_1().test(t);
		new TestGeneric0_1().test(i);
		new TestGeneric0_1().test(o);
	}
	
	
//	  boolean containsAll(Collection<?> c){
//		  
//	  }
//
//	    boolean addAll(Collection<? extends T> c){
//	    	
//	    }
	
	 <T> boolean containsAll(Collection<T> c) {
		return false;
	}

     <T extends Object> boolean addAll(Collection<T> c) {
		return false;
	}
}
