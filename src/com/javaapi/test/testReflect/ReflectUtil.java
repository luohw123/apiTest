package com.javaapi.test.testReflect;

public class ReflectUtil {
	/**
	 * 范型得处理
	 * 
	 * @param <T>
	 * 
	 * @param fqn
	 * @param t
	 * @return
	 */
	 public static <T> T getInstance(Class<T> t) {
		 T instance = null;
	 try {
		instance = t.newInstance();
	 } catch (SecurityException e) {
	 e.printStackTrace();
	 } catch (IllegalArgumentException e) {
	 e.printStackTrace();
	 } catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}
	 return instance;
	 }
}
