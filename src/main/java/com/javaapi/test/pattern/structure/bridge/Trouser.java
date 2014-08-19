package com.javaapi.test.pattern.structure.bridge;

public class Trouser extends Clothing {

	public void personDressCloth(Person person) {
		System.out.println(person.getType() + "穿裤子");
	}
}
