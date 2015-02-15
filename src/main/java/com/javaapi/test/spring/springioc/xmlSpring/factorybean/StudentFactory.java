package com.javaapi.test.spring.springioc.xmlSpring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class StudentFactory implements FactoryBean<Student>{

	@Override
	public Student getObject() throws Exception {
		Student student = new Student();
		student.setName("kkFactory");
		student.setAge("19");
		return student;
	}

	@Override
	public Class<?> getObjectType() {
		return Student.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
