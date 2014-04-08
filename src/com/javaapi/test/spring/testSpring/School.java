package com.javaapi.test.spring.testSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class School {
	@Autowired
	public WorkerI teacher;
}
