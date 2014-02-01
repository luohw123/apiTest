package com.javaapi.test.Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generic3<T> {
    public Generic3<T> g = new Generic3<T>();

    public static void main(String[] args) {

        List<Rectangle> list = new ArrayList<Rectangle>();
        list.add(new Rectangle());
        list.add(new Rectangle());
        list.add(new Rectangle());
        new Generic3().getXianZhiTPF(list); // 通配符,可以传送
        new Generic3().getTPF(list); // 有限制的通配符 ,可以向里传送

        Collection cs = new ArrayList<String>();

        if (cs instanceof Collection) {

        } // 非法
    }

    public String getXianZhiTPF(List<?> shapes) {
        // shapes.add(new Object()) //通配符不可以添加子类
        for (Object object : shapes) { // 通配符解开包只能是Object 类型
            System.out.println(object.toString());
        }
        return null;
    }

    public String getTPF(List<? extends Shape> shapes) {
        // shapes.add(0, new Rectangle()); //有限制的通配符也不可以增加传递子类
        for (Shape shape : shapes) { // 有限制的通配符解开包可以是具体类型
            System.out.println(shape.toString());
        }
        return null;
    }

}
