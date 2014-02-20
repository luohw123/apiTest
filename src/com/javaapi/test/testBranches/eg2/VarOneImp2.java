package com.javaapi.test.testBranches.eg2;

public class VarOneImp2 implements VarOne {
    @Override
    public String getName() {
        System.out.println(this.getClass().getName());
        return null;
    }
}
