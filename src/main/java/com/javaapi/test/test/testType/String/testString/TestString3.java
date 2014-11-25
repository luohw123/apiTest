package com.javaapi.test.test.testType.String.testString;

public class TestString3 {
    public static void main(String args[]) {
        // 在池中和堆中分别创建String对象"abc",s1指向堆中对象
        String s1 = new String("abc");
        // s2直接指向池中对象"abc"
        String s2 = "abc";
        // 在堆中新创建"abc"对象，s3指向该对象
        String s3 = new String("abc");
        // 在池中创建对象"ab" 和 "c"，并且s4指向池中对象"abc"
        String s4 = "ab" + "c";
        // c指向池中对象"c"
        String c = "c";
        // 在堆中创建新的对象"abc"，并且s5指向该对象
        String s5 = "ab" + c;

        String s6 = "ab".concat("c"); // 使用concat 不会加入常量池
        String s7 = "ab".concat(c);
        String s8 = new String(new char[] { 'a', 'b', 'c' });
        String s9 = new String(new char[] { 'a', 'b', 'c' });
        String s10 = new String(new char[] { 'a', 'b', 'c' });
        String s11 = new String(new char[] { 'a' })
                + new String(new char[] { 'b' })
                + new String(new char[] { 'c' });
        String s12 = new String(new char[] { 'c' }).intern(); // 与s2内存地址不同,说明变量+号俩边,有变量(而且不是静态变量)就不会在字符串池中找
        String s13 = "ab" + s12;
        System.out.println("------------实串-----------");
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // false
        // s2 是池中的"abc"
        System.out.println(s2 == s3); // false
        System.out.println(s2 == s4); // true
        System.out.println(s2 == s5); // false
        System.out.println(s2 == s6); // false
        System.out.println(s2 == s7); // false
        System.out.println(s2 == s8.intern()); // true
        System.out.println(s2 == s8); // false
        System.out.println(s2 == s9); // false
        System.out.println(s2 == s10); // false
        System.out.println(s2 == s11); // false
        System.out.println(s6 == s7); // false
        System.out.println(s6 == s7); // false
        System.out.println(s2 == s13); // false
        System.out.println(s12 == c); // true
        String b1 = new String("");
        String b2 = "";
        String b3 = new String("");
        String b4 = "".intern();
        String b5 = "" + "";
        String b6 = "".concat("");
        String b7 = "  ".trim();
        String b8 = "  ";
        String b9 = "    ".trim();

        System.out.println("------------空串-----------");
        System.out.println(b1 == b2); // false
        System.out.println(b1 == b3); // false
        System.out.println(b2 == b3); // false
        System.out.println(b2 == b4); // true
        System.out.println(b2 == b5); // true*
        System.out.println(b2 == b6); // true*
        System.out.println(b2 == b7); // false*
        System.out.println("-----a----");
        System.out.println(b2.equals(b7)); // true
        System.out.println(b7 == b8); // false
        System.out.println(b7 == b9); // false
        System.out.println(b7.equals(b9)); // true
        System.out.println(b9 == null);// false

        System.out.println("b8.trim():");
        for (byte b : b8.getBytes()) {
            System.out.print(">>>" + (int) b + " ");
        }
        System.out.println("\nb8.trim():");
        for (byte b : b8.trim().getBytes()) {
            System.out.print(">>>" + (int) b + " ");
        }
        System.out.println("\nb9.trim():");
        for (byte b : b9.trim().getBytes()) {
            System.out.print(">>>" + (int) b + " ");
        }
    }
}