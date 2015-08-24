package com.javaapi.test.test.testJavaFeature.innerClass;

import org.junit.Test;

/**
 * 测试外部类读取内部类属性 </br> 1 无论内部类修饰符如何,或者属性如何,外部类都可以直接访问内部类属性 </br>
 * 
 * 
 */
public class ClientOuterReadInner {

	/**
	 * 
	 */
	@Test
	public void testInitInOuter() {
		ClientInnerPrivate outer = new ClientOuterReadInner.ClientInnerPrivate();
		System.out.println(outer.getName());
		ClientInnerPrivate inner = new ClientInnerPrivate();
		System.out.println(inner.getName());
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
		{
			name = "ClientInnerPrivate";
		}
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

	public class ClientInnerPublic {
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
    public static class ClientStaticInnerPublic {
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
