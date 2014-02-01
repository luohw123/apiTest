package com.javaapi.test.Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generic4 {
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // correct
        }
    }

    /**
     * 带多种泛型的方法
     * 
     * @param a
     * @param c
     * @param d
     */
    static <T, S, R> void fromArrayToCollection(T[] a, Collection<S> c,
            List<R> d) {
        for (T o : a) {
            // c.add(o); // correct
        }
    }

    public <T> void fromArrayToCollection(T a) {
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<Object>();
        fromArrayToCollection(oa, co);// T 指Object
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<String>();
        fromArrayToCollection(sa, cs);// T inferred to be String
        fromArrayToCollection(sa, co);// T inferred to be Object
        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<Number>();
        fromArrayToCollection(ia, cn);// T inferred to be Number
        fromArrayToCollection(fa, cn);// T inferred to be Number
        fromArrayToCollection(na, cn);// T inferred to be Number
        fromArrayToCollection(na, co);// T inferred to be Object
        // fromArrayToCollection(na, cs);// compile-time error
    }
}
