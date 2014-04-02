package com.javaapi.test.genericType;

/**
 * 泛型的应用包括 泛型类和泛型方法
 * 
 * 这是泛型类得应用方法
 */
public class TestGeneric<T> {
	private T t;
	private String information;

	/**
	 * Description: default constructor. To get an object of the generic class
	 */
	public TestGeneric(T oT) {
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
		TestGeneric<String> t = new TestGeneric<String>(string);
		System.out.println(t.getClassType());
		TestGeneric<Integer> i = new TestGeneric<Integer>(integer);
		System.out.println(i.getClassType());
	}
	// 省略了set/get方法
}