package com.javaapi.test.testUtil.apache.commonsLang;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**q
 * Created by user on 15/8/16.
 */
public class TestReflectionToStringBuilder {

    @Test
    public void test(){
        Person p = new Person();
        p.setAge("18");
        p.setName("kk");
        String s = ReflectionToStringBuilder.toString(p);
        System.out.println(s);
    }
    /*不可以打印list中的对象*/
    @Test
    public void testList() {
        List<Person> list = new ArrayList<>();
        String s = ReflectionToStringBuilder.toString(list);
        System.out.println(s);
        String s1 = ReflectionToStringBuilder.reflectionToString(list);
        System.out.println("s1 = " + s1);
    }

}
