package com.javaapi.test.dao.constant.Game.classconstant;

import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    ConstantGame constantGame;
    String name;
    String id;
    int age;


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student [constantGame=" + constantGame + ", name=" + name + ", id=" + id + ", age=" + age + "]";
    }

    public ConstantGame getConstantGame() {
        return constantGame;
    }

    public void setConstantGame(ConstantGame constantGame) {
        this.constantGame = constantGame;
    }

}