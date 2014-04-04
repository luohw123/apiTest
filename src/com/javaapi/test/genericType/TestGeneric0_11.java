package com.javaapi.test.genericType;

import java.util.Collection;

public class TestGeneric0_11<E> {
//	 boolean containsAll(Collection<?> c) {
//		return false;
//	}
//
//	    boolean addAll(Collection<? extends E> c) {
//			return false;
//		}
	
	// 下面这俩个方法可以代替上面俩个方法
        <T> boolean containsAll(Collection<T> c) {
			return false;
		}

        <T extends E> boolean addAll(Collection<T> c) {
			return false;
		}


}
