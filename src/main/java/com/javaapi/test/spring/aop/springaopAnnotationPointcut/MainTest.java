package com.javaapi.test.spring.aop.springaopAnnotationPointcut;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class MainTest {

    @Autowired
    private ApplicationContext context;

    // 在xml里配置了    <aop:aspectj-autoproxy  proxy-target-class="true"/>,通过获取原bean的名字就可以获取到代理对象

    @Test
    public void testNameBook() throws Exception {
        Book book = (Book) context.getBean("book");

        System.out.println("---------------------");

        book.printName();

        System.out.println("---------------------");

        book.printUrl();

        System.out.println("----------------------");

        try {

            book.printThrowException();

        } catch (Exception e) {
            //  e.printStackTrace();
        }


    }


}
