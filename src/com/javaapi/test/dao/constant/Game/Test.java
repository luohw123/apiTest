package com.javaapi.test.dao.constant.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *http://wenku.baidu.com/view/3e87250e844769eae009ed53.html 
 *http://mysun.iteye.com/blog/1581119
 *http://sammor.iteye.com/blog/1052055?page=2#comments
 * @project apiTest
 * @author kk
 * @date 2014年7月28日
 */
public class Test {
    private static String file = "/serialFile.txt";

    public static void main(String[] args) throws Exception {
        write();
        //read();
    }

    static void write() throws Exception {
        FileOutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Student s = new Student();
        s.setAge(20);
        s.setName("test");
        s.setState(State.TEST);
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