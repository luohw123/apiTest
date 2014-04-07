package com.javaapi.test.testlogic.testBranches.eg;

public class TeacherImp implements PeopleI {

    @Override
    public String getName() {
        String name = "teacher";
        System.out.println(name);
        return name;
    }

}
