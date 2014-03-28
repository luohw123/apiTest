package com.javaapi.test.pattern.structure.bridge;

public abstract class Person {

	private Clothing clothing;

	private String type;

	public Clothing getClothing() {
		return clothing;
	}

	public void setClothing() {
		this.clothing = new Trouser();
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public abstract void dress();
}
