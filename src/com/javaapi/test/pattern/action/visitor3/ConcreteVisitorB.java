package com.javaapi.test.pattern.action.visitor3;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-13下午11:32:55 
 *描述：具体访问者B 
 */  
public class ConcreteVisitorB implements Visitor{  
  
    @Override  
    public void visit(ConcreteElementB able) {  
    	System.out.println(this.toString());
        able.operate();  
    }  
  
    @Override  
    public void visit(ConcreteElementA able) {  
    	System.out.println(this.toString());
        able.operate();  
    }  
  
      
  
}  