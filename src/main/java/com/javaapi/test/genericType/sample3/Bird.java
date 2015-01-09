package com.javaapi.test.genericType.sample3;

public class Bird extends Animal {

	public Bird(String name) {
		super(name);
	}

	public void fly() {
		System.out.println(getName() + " can fly.");
	}
}
