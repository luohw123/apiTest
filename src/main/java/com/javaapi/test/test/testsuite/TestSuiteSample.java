package com.javaapi.test.test.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.javaapi.test.spring.springioc.annotationSpring.Client;
import com.javaapi.test.test.testmockito.MockitoSampleTest;
import com.javaapi.test.test.testmockito.TestMockito;

@RunWith(Suite.class)
@SuiteClasses(value = { TestMockito.class, MockitoSampleTest.class,
		Client.class })
public class TestSuiteSample {

}
