package com.javaapi.test.dao.constant.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *http://wenku.baidu.com/view/3e87250e844769eae009ed53.html </br>
 *http://mysun.iteye.com/blog/1581119</br>
 *http://sammor.iteye.com/blog/1052055?page=2#comments</br>
 * 1.反序列化时如果是减少或增加属性（包括枚举），方法，不会有问题  </br>
 * 2.如果要反序列化枚举实例时，程序端有该枚举类型但没有该实例，则抛错。</br>
 * 3.序列化版本号不对，抛错。
 * @project apiTest
 * @author kk
 * @date 2014年7月28日
 */
public class Test {
    private static String file = "/home/kk/program/serialFile.txt";

    public static void main(String[] args) throws Exception {
        //        write();
        read();
        System.out.println("done");
    }

    static void write() throws Exception {
        FileOutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Student s = new Student();
        s.setAge(20);
        s.setName("test");
        s.setState(State.COMMON);
        oos.writeObject(s);
        oos.close();
    }

    static void read() throws Exception {
        FileInputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        Student s = (Student) ois.readObject();
        System.out.println(s);
    }
}