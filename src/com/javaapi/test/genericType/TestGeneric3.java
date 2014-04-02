package com.javaapi.test.genericType;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric3 {
	/**
	 * 注意泛型没有继承说
	 */
	public void test1() {
		// 编译不通过
		List<Object> list = new ArrayList<Integer>();
		// or
		List<Object> list2 = new ArrayList<Double>();
	}

	/**
	 * 上面代码的业务逻辑为有一组元素,但我不确定元素时整形还是浮点型,当我在确定后创建对应的泛型实现类,刚接触泛型的读者有可能会犯以上错误,
	 * 理解为泛型之间是可以继承的
	 * ,例如声明Object的泛型实际上创建Integer泛型,错误认为泛型之间也存在继承关系,但这是不正确的,泛型是帮助开发者编译期间类型检查安全
	 * .我们可以换种方式实现以上业务逻辑
	 */
	public void test2() {
		// Number 为Integer 和 Double 的公共父类
		List<Number> numberList = new ArrayList<Number>();

		// 使用通配符,代表实际类型是Number类型的子类
		List<? extends Number> numberList2 = new ArrayList<Integer>();
		// or
		List<? extends Number> numberList3 = new ArrayList<Double>();
	}
}
