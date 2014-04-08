package com.javaapi.test.spring.testSpring;

import org.springframework.stereotype.Service;

@Service
public class TeacherImp implements WorkerI {

	@Override
	public void work() {
		System.out.println(this.getClass().getName());
	}

}
