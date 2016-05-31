package com.javaapi.test.test.testJavaFeature.innerClass.featureInit;

import org.junit.Test;

/**
 * java内部类的初始化
 * 参考 http://blog.csdn.net/songdexv/article/details/6758972
 * http://www.cnblogs.com/dolphin0520/p/3811445.html
 *
 *
 *
 **/
public class InnerClassTest {

    /**
     * 普通内部类 初始化需要依赖外部类实例
     */
    @Test
    public void testInner() {
        InnerClassTest.Inner inner = new InnerClassTest().new Inner();
        inner.show();
    }

    /**
     * 静态内部类初始化,不需要外部类实例
     * @throws Exception
     */
    @Test
    public void testStaticInner() throws Exception {
        InnerClassTest.StaticInner staticInner = new InnerClassTest.StaticInner();
        staticInner.show();
    }

    private static class StaticInner {
        public void show() {
            System.out.println("static inner class show");
        }
    }

    private class Inner {
        public void show() {
            System.out.println("inner class show");
        }
    }
}
