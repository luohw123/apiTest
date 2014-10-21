package com.javaapi.test.test.testJavaFeature.innerClass;

import org.junit.Test;

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
