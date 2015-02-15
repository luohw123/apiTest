package com.javaapi.test.pattern.structure.decorator.mytest;

public class ManOper3 implements Person{
    
    private Person person;
    
    @Override
    public void eat() {
        person.eat();
        System.out.println("再吃3");
    }



    public Person getPerson() {
        return person;
    }



    public void setPerson(Person person) {
        this.person = person;
    }

}
