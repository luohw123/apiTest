package com.javaapi.test.spring.aop.springaop2;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

@Component("greetingIntroAdvice")
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}