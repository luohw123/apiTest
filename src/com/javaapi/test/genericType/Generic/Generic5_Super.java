package com.javaapi.test.genericType.Generic;

import java.util.ArrayList;
import java.util.List;

public class Generic5_Super {
    public static void main(String[] args) {
        List<Integer> aa = new ArrayList<Integer>();
        aa.add(123);
        aa.add(123);
    }

    public static void write(List<? super Number> list) {
        list.add(123);
        list.add(123.0);
        list.add(Double.parseDouble("123"));

    }

    // public static void main(String[] args) {
    // // Number 为Integer 和 Double 的公共父类
    // List<Number> numberList = new ArrayList<Number>();
    //
    // // 使用通配符,代表实际类型是Number类型的子类
    // List<? super Integer> numberList2 = new ArrayList<Integer>();
    // // or
    // List<? super Number> numberList3 = new ArrayList<Double>();
    // }
}
