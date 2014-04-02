package com.javaapi.test.testReflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectUtil {
	/**范型得处理
	 * @param fqn
	 * @param t
	 * @return
	 */
	public static  <T> T getInstance(String fqn,T t) {
		try {
			Class<?> peopleClass = Class
					.forName(fqn);
			T people = (T) peopleClass.getConstructor(String.class)
					.newInstance("nihao");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
		return t;

	}
}
