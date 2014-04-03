package com.javaapi.test.genericType;

/**
 * 
 * 
 * 泛型类
 */
public class TestGeneric0_0<T> {
	private T t;
	private String information;

	/**
	 * Description: default constructor. To get an object of the generic class
	 */
	public TestGeneric0_0(T oT) {
		this.t = oT;
	}

	/**
	 * @param sBrand
	 *            : brand of the goods
	 * @param sName
	 *            : name of the goods
	 * @param sPrice
	 *            : price of the goods Description: set the data for the object
	 */
	public void setData(String sBrand, String sName, String sPrice) {
		this.information = "This " + sName + " of " + sBrand + " costs "
				+ sPrice + "!";
	}

	public String getClassType() {
		return t.getClass().getName();
	}

	public static void main(String[] args) {
		String string = "aa";
		Integer integer = 0;
		TestGeneric0_0<String> t = new TestGeneric0_0<String>(string);
		System.out.println(t.getClassType());
		TestGeneric0_0<Integer> i = new TestGeneric0_0<Integer>(integer);
		System.out.println(i.getClassType());
	}
	// 省略了set/get方法
}