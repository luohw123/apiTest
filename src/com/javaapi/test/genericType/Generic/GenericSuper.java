package com.javaapi.test.genericType.Generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型super关键字
 *
 */
public class GenericSuper {
    public static void main(String[] args) {
        List<Integer> aa = new ArrayList<Integer>();
        aa.add(123);
        aa.add(123);
        // 报错,write只能放入Number的父类的集合
//        write(aa);
        List<Number> bb= new ArrayList<Number>();
        bb.add(123);
        bb.add(123);
        // 正确,放入本身是可以的
        write(bb);
        List<Object> cc = new ArrayList<Object>();
        cc.add(new Object());
        cc.add(new Object());
        // 正确
        write(cc);
    }

    public static void write(List<? super Number> list) {
    	// 查询的时候只能转为Object,因为不知道具体的类型
    	for (Object object : list) {
    		if(object instanceof Number){
    			System.out.println((Number)object);
    		}
		}
    	// 插入的时候可以放入子类
        list.add(123);
        list.add(123.0);
        list.add(Double.parseDouble("123"));
    }
}