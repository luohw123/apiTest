package com.javaapi.test.application.test.testmockito;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.javaapi.test.spring.springioc.annotationSpring.Client;

/**
 * 这是junit得一系列文章</br>
 * http://blog.csdn.net/luanlouis/article/details/37564355</br>
 * 
 */
@RunWith(Suite.class)
@SuiteClasses(value = { TestMockito.class, MockitoSampleTest.class,
		Client.class })
public class TestSuiteSample {

}
