package com.javaapi.test.testBranches;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.javaapi.test.testBranches.eg.LeaderImp;
import com.javaapi.test.testBranches.eg.PeopleI;
import com.javaapi.test.testBranches.eg.StudentImp;
import com.javaapi.test.testBranches.eg.TeacherImp;
import com.javaapi.test.testBranches.eg.WorkerImp;
import com.javaapi.test.testBranches.eg2.VarOne;
import com.javaapi.test.testBranches.eg2.VarOneImp1;
import com.javaapi.test.testBranches.eg2.VarOneImp2;

public class TestBranches {
    /**
     * 用来获取分支代码段,这是很普通的if 分支判断;会导致当前代码臃肿
     */
    @Test
    public void testif() {
        String var = "one";
        if (var.equals("one")) {
            System.out.println("这是1");
        } else if (var.equals("two")) {
            System.out.println("这是2");
        } else if (var.equals("three")) {
            System.out.println("这是3");
        }

    }

    /**
     * 用来获取分支代码段,switch 支持int,enum,string(java 7才支持);会导致当前代码臃肿
     */
    @Test
    public void testSwitch() {
        String var = "one";
        switch (var) {
            case "one":
                System.out.println("one");
                break;
            case "two":
                System.out.println("two");
                break;
            case "three":
                System.out.println("three");
                break;
            case "four":
                System.out.println("four");
                break;
            case "five":
                System.out.println("five");
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    /**
     * 根据不同变量; 用来获取分支代码段,用函数的多态(使代码段可在不同文件中进行扩展,不至于使当前代码臃肿 但是
     * 但是一般情况传入的参数值不是对象,而是基本类型,例如表单里的姓名(String),年龄(int)
     **/
    @Test
    public void testFunction() {
        PeopleI var;
        var = new TeacherImp();
        var.getName();
        var = new WorkerImp();
        var.getName();
        var = new StudentImp();
        var.getName();
        var = new LeaderImp();
        var.getName();
    }

    @Test
    public void testMapAndFunction() {
        String var = "one";
        Map<String, PeopleI> map = new HashMap<String, PeopleI>();
        map.put("one", new TeacherImp());
        map.put("two", new WorkerImp());
        map.put("three", new StudentImp());
        map.put("four", new LeaderImp());
        map.get(var).getName();
    }

    /**
     * 用来获取变量(变量是代码段的特例)
     */
    @Test
    public void testName() {
        String var = "one";
        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");
        System.out.println("--------------------");
        String value = map.get(var);
        System.out.println(value);
    }

    /**
     * 层级,多维度判断 ,嵌套if(五个维度,每个维度深度为2的时候:1 代码阅读起来非常臃肿;2编写的时候也非常耗时(通过人脑判断)3 维护
     * :要是再添加维度需要再(通过人脑判断)添加32种情况(2的5次方为32,2的6次方为64)(非常糟糕)
     **/
    @Test
    public void testQianTao() {
        String varOne = "one";
        String varTwo = "two";
        String varThree = "three";
        String varFour = "four";
        String varFive = "five";
        System.out.println("---------");
        String one = "one";
        String two = "two";
        if (varOne.equals(one)) {
            if (varTwo.equals(one)) {
                if (varThree.equals(one)) {
                    if (varFour.equals(one)) {
                        if (varFive.equals(one)) {
                            System.out.println("特性1");
                        } else if (varFive.equals(two)) {

                        }
                    } else if (varFour.equals(two)) {

                    }
                } else if (varThree.equals(two)) {

                }
            } else if (varTwo.equals(two)) {

            }
        } else if (varOne.equals(two)) {

        }
    }

    /**
     * 层级,多维度判断 ,平行if(五个维度,每个维度深度为2的时候:1 代码阅读起来非常臃肿;2编写的时候也非常耗时 3 维护
     * :要是再添加维度需要再(通过人脑判断)添加32种情况(2的5次方为32,2的6次方为64)(非常糟糕)
     **/
    @Test
    public void testLogic() {
        String varOne = "one";
        String varTwo = "two";
        String varThree = "three";
        String varFour = "four";
        String varFive = "five";
        System.out.println("---------");
        String one = "one";
        String two = "two";
        if (varOne.equals(one) && varTwo.equals(one) && varTwo.equals(one)
                && varThree.equals(one) && varFour.equals(one)
                && varFive.equals(one)) {

        } else if (varOne.equals(one) && varTwo.equals(one)
                && varTwo.equals(one) && varThree.equals(one)
                && varFour.equals(one) && varFive.equals(one)) {

        } else if (varOne.equals(one) && varTwo.equals(one)
                && varTwo.equals(one) && varThree.equals(one)
                && varFour.equals(one) && varFive.equals(one)) {

        } else if (varOne.equals(one) && varTwo.equals(one)
                && varTwo.equals(one) && varThree.equals(one)
                && varFour.equals(one) && varFive.equals(one)) {

        } else if (varOne.equals(one) && varTwo.equals(one)
                && varTwo.equals(one) && varThree.equals(one)
                && varFour.equals(one) && varFive.equals(one)) {

        } else if (varOne.equals(one) && varTwo.equals(one)
                && varTwo.equals(one) && varThree.equals(one)
                && varFour.equals(one) && varFive.equals(one)) {

        }
        // ...其余26种情况
    }

    /**
     * 1 需要为VarXXX类们的方法增加一个统一的参数对象. 2 可以支持按顺序调用 3 类似bit位,同时判断多种属性
     */
    @Test
    public void getName() {
        String paramone = "one";
        String paramtwo = "one";
        String paramthree = "one";
        String paramfour = "one";
        String paramfive = "one";
        Map<String, VarOne> mapOne = new HashMap<String, VarOne>();
        // Map<String, VarThree> mapThree = new HashMap<String, VarThree>();
        // Map<String, VarFour> mapFour = new HashMap<String, VarFour>();
        // Map<String, VarFive> mapFive = new HashMap<String, VarFive>();
        // // 区分下一个维度的字段,可以通过set方法或者构造函数传入成员变量,也可以通过方法参数传入.方式不限
        System.out.println("维度---------1");
        mapOne.put("one", new VarOneImp1());
        mapOne.put("two", new VarOneImp2());
        //
        // System.out.println("维度---------3");
        // mapThree.put("one", new VarThreeImp1());
        // mapThree.put("two", new VarThreeImp2());
        //
        // System.out.println("维度---------4");
        // mapFour.put("one", new VarFourImp1());
        // mapFour.put("two", new VarFourImp2());
        //
        // System.out.println("维度---------5");
        // mapFive.put("one", new VarFiveImp1());
        // mapFive.put("two", new VarFiveImp2());

        mapOne.get(paramone).getName(paramone);

    }
}
