package com.javaapi.test.spring.aop.springaop2AutoProxy1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class GreetingAroundAdvice2  implements MethodInterceptor{
	   @Override
	    public Object invoke(MethodInvocation invocation) throws Throwable {
	        before();
	        Object result = invocation.proceed();
	        after();
	        return result;
	    }

	    private void before() {
		System.out.println("Before -->2");
	    }

	    private void after() {
		System.out.println("After-->2");
	    }
	
}