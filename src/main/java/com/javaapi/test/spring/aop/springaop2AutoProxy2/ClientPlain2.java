package com.javaapi.test.spring.aop.springaop2AutoProxy2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 多个动态代理可以对一个bean进行多次包装而互不影响
 */
public class ClientPlain2 {


    @Test
    public void test1() {
        String path = ClientPlain2.class.getResource("").getPath();
        String apppath = "file:" + path + "applicationContext2.xml";
        ApplicationContext app = new FileSystemXmlApplicationContext(apppath);
        GreetingImpl bean = (GreetingImpl) app.getBean("greetingImpl");
        bean.goodMorning("kk");
        System.out.println("----------------");

    }


}