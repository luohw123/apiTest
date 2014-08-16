package com.javaapi.test.testlogic.testBranches.eg;

public class StudentImp implements PeopleI {

    @Override
    public String getName() {
        String name = "student";
        System.out.println(name);
        return name;
    }
}
