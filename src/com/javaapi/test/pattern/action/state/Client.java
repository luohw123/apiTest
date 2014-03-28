package com.javaapi.test.pattern.action.state;
//很容易让人想到的一种使用方式.
public class Client {

	public static void main(String[] args) {
		// 创建状态
		State state = new ConcreteStateB();
		// 创建环境
		Context context = new Context();
		// 将状态设置到环境中
		context.setState(state);
		// 请求
		context.request("test");
	}
}