package com.javaapi.test.testReflect.serializable.ser1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    // 注意此处没有提供无参数的构造器 
    public Person3(String name, int age) {
        super();
        System.out.println("无参数的构造器");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // 将name Field值反转后写入二进制流 
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // 将去读的字符串反转后赋值给name Field 
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }
}