package com.javaapi.test.test.testJavaFeature.innerClass;

import org.junit.Test;

/**
 * 测试外部类读取内部类属性 </br> 1 无论内部类修饰符如何,或者属性如何,外部类都可以直接访问内部类属性 </br>
 * 
 * 
 */
public class Client {
	@Test
	public void testPrivate() {
		ClientInnerPrivate clientInner = new ClientInnerPrivate();
		clientInner.setName("testPrivate");
		System.out.println(clientInner.getName());
		System.out.println(clientInner.name);
	}

	@Test
	public void testProtected() {
		ClientInnerProtected clientInner = new ClientInnerProtected();
		clientInner.setName("testProtected");
		System.out.println(clientInner.getName());
		System.out.println(clientInner.name);
	}

	private class ClientInnerPrivate {
		private String	name;
		private String	age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

	}

	protected class ClientInnerProtected {
		private String	name;
		private String	age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

	}
}
