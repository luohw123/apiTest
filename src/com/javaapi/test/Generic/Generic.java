package com.javaapi.test.Generic;


public class Generic<T> {
    public String getType(T t) {
        System.out.println(t.getClass());
        return null;
    }

    public static void main(String[] args) {
        new Generic<Integer>().getType(15);
        new Generic<String>().getType("15");
        new Generic<Double>().getType(15.0);
        System.out.println("----------------------");
    }
}
