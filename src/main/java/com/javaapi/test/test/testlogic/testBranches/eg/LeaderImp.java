package com.javaapi.test.test.testlogic.testBranches.eg;

public class LeaderImp implements PeopleI {

    @Override
    public String getName() {
        String name = "leader";
        System.out.println(name);
        return name;
    }

}
