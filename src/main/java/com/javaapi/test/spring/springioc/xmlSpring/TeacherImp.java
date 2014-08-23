package com.javaapi.test.spring.springioc.xmlSpring;


public class TeacherImp implements WorkerI {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void work() {
		String x = name+"正在上课";
		System.out.println(x);
	}

}
