package com.javaapi.test.test.testJavaFeature.innerClass;

import org.junit.Test;

/**
 * http://www.cnblogs.com/dolphin0520/p/3811445.html （好）
 * 
 * 
 */
public class Client2 {

    /**
     * http://www.sjsjw.com/kf_mobile/article/030656ABA002590.asp
     */
    @Test
    public void testOuterOuterReadInnerPrivate(){
//        ClientInnerPrivate inner = new ClientInnerPrivate(); // 找不到

    }
    @Test
    public void testOuterOuterReadProtect(){
//        ClientOuterReadInner.ClientInnerProtected clientInner = new ClientOuterReadInner.ClientInnerProtected();  //error
        ClientOuterReadInner outer = new ClientOuterReadInner();
        ClientOuterReadInner.ClientInnerProtected outerProtect = outer.new ClientInnerProtected();
        outer.testInitInOuter();
    }
    @Test
    public void testOuterOuterReadPublic(){
//        ClientOuterReadInner.ClientInnerProtected clientInner = new ClientOuterReadInner.ClientInnerProtected();  //error
        ClientOuterReadInner outer = new ClientOuterReadInner();
        ClientOuterReadInner.ClientInnerPublic outerProtect = outer.new ClientInnerPublic();
        outer.testInitInOuter();
    }

    /**
     * 不需要外部类实例，但是还是需要外部类.内部类
     */
    @Test
    public void testClientStaticInnerPublic(){
        ClientOuterReadInner.ClientStaticInnerPublic c = new ClientOuterReadInner.ClientStaticInnerPublic();
        System.out.println("c = " + c);
    }
}
