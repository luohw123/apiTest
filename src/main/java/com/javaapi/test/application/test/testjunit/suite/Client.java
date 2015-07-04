package com.javaapi.test.application.test.testjunit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ATest.class,BTest.class})
public class Client {

}
