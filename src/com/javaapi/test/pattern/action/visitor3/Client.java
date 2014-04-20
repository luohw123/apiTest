package com.javaapi.test.pattern.action.visitor3;

import java.util.ArrayList;
import java.util.List;

/** 
 *  
 *  运行下试试</br>
 *  http://alaric.iteye.com/blog/1942517
 */  
public class Client {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
        Visitor v1 = new ConcreteVisitorA();  
        List<Visitable> list = new ArrayList<>();  
        list.add(new ConcreteElementA());  
        list.add(new ConcreteElementB());  
        for(Visitable able :list){  
            able.accept(v1);  
        }  
      
    }  
  
}  