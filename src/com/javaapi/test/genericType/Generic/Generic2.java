package com.javaapi.test.genericType.Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generic2 {
    public String getDiffType() {
        Collection<?> a;
        List<String> d = new ArrayList<String>();
        d.add("1");
        d.add("12");
        d.add("13");
        printCollection(d);
        a = d;
        printCollection(a);
        return null;

    }

    void printCollection(Collection<?> c) {
        for (Object a : c) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        new Generic2().getDiffType();
    }
}
