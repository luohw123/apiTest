package com.javaapi.test.spring.aop.aspectj;

public interface CustomerBo {
	 
	void addCustomer();
 
	String addCustomerReturnValue();
 
	void addCustomerThrowException() throws Exception;
 
	void addCustomerAround(String name);
}