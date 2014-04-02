package com.javaapi.test.genericType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 泛型函数 :泛型函数允许类型参数被用来表示方法的一个或多个参数之间的依赖关系，或者参数与其返回值的依赖关系。如果没有这样的依赖关系，不应该使用泛型方法。
 * 
 * @author wk
 * 
 */
public class TestGenericType {
	static Integer a;
	static {
		System.out.println("==>" + a);
	}

	static <T> void fromArrayToCollection(T[] a, Collection<T> c) {

		for (T o : a) {
			System.out.println(o);
			c.add(o); // correct
		}

	}

	public static void main(String[] args) {
		Object[] oa = new Object[100];

		Collection<Object> co = new ArrayList<Object>();

		fromArrayToCollection(oa, co);// T 指Object

		String[] sa = new String[100];

		Collection<String> cs = new ArrayList<String>();

		fromArrayToCollection(sa, cs);// T inferred to be String

		fromArrayToCollection(sa, co);// T inferred to be Object

		Integer[] ia = new Integer[100];

		Float[] fa = new Float[100];

		Number[] na = new Number[100];

		Collection<Number> cn = new ArrayList<Number>();

		fromArrayToCollection(ia, cn);// T inferred to be Number

		fromArrayToCollection(fa, cn);// T inferred to be Number

		fromArrayToCollection(na, cn);// T inferred to be Number

		fromArrayToCollection(na, co);// T inferred to be Object

	}
}
