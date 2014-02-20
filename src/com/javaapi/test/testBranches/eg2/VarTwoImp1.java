package com.javaapi.test.testBranches.eg2;

public class VarTwoImp1 implements VarTwo {
    @Override
    public String getName() {
        System.out.println(this.getClass().getName());
        return null;
    }
}
