package com.javaapi.test.dao.constant.Game.classconstant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * 使用自定义的类代替枚举可以正常反序列化 
 * @project apiTest
 * @author kk
 * @date 2014年7月31日
 */
public class TestConstant {
    private static final String file = "/home/kk/program/serialFile.txt";

    /**
     * 应该不提供set方法,否则外部能修改常量值
     */
    @Test
    public void testConstantModify() {
//    	ConstantGame.SJ.setIndex(2);
    	System.out.println(ConstantGame.SJ.getIndex());
    }
    
    @Test
    public void testConstant() {
        System.out.println(ConstantGame.SJ.getIndex());
        System.out.println(ConstantGame.SJ);
        System.out.println(ConstantGame.SJ.SJ);
        System.out.println(ConstantGame.SJ.SJ.SJ);
        System.out.println(ConstantGame.SJ == ConstantGame.SJ.SJ.SJ);
    }

    @Test
    public void write() throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Student s = new Student();
        s.setAge(20);
        s.setName("test");
        s.setConstantGame(ConstantGame.CYHX);
        oos.writeObject(s);
        oos.close();
        System.out.println("done");
    }

    @Test
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        Student s = (Student) ois.readObject();
        System.out.println(s);
        System.out.println(ConstantGame.CYHX.getDescription());
        System.out.println(s.getConstantGame().getDescription());
        System.out.println(s.getConstantGame() == ConstantGame.CYHX);
        System.out.println("done");
    }
}
