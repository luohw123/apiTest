package com.javaapi.test.test.genericType.sample3;

public class Magpie extends Bird {

	public Magpie(String name) {
		super(name);
	}

	public void sing(){
		System.out.println(getName() + 
				" can not only eat,but sing");
	}
}