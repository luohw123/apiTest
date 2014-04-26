package com.javaapi.test.testProxy.cglib.cglibaop;

import java.util.ArrayList;
import java.util.List;

import com.javaapi.test.testProxy.cglib.Person;

public class Client {
	  
    public static void main(String[] args) {  
    	List<Aop> list=new ArrayList<Aop>();
    	list.add(new AopImp());
    	list.add(new MyAopEg());
        // 未实现接口的类的代理  
        Person person = CglibProxy.proxyTarget(new Person(),list);  
        person.active("Tom speaking ........");  
        // 实现接口的类的代理  
//        IAnimal dog = CglibProxy.proxyTarget(new Dog(),list);  
//        dog.active("Dog is running.........");  
    }  
}
