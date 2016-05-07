package com.javaapi.test.test.testJavaFeature.Version_1_8.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *http://ifeve.com/stream/
 */
public class ClientMap {

    private List<Person> phpProgrammers;
    private List<Person> javaProgrammers;

    @Before
    public void setUp() {
        javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
    }

    @Test
    public void testMap() throws Exception {
        List<String> collect = javaProgrammers.stream().map((s) -> s.getFirstName()).collect(Collectors.toList());
        System.out.println("collect = " + collect);

    }

    @Test
    public void testReduce() throws Exception {
        Person person = javaProgrammers.stream().reduce((a, b) -> {
            if (a.getAge() - b.getAge() < 0) {
                return a;
            } else {
                return b;
            }
        }).get();
        System.out.println("person = " + person);

    }
    @Test
    public void testGrouppingBy1() throws Exception {
        Map<String, List<Person>> collect = javaProgrammers.stream().collect(Collectors.groupingBy((s) -> s.getGender()));
        System.out.println(collect);
    }

    @Test
    public void testGrouppingBy2() throws Exception {
        Collector<Person, ?, List<String>> mapping = Collectors.mapping((s) -> s.getFirstName(), Collectors.toList());
        Map<String, List<String>> collect = javaProgrammers.stream().collect(Collectors.groupingBy((s) -> s.getGender(), mapping));
        System.out.println("collect = " + collect.getClass());
        collect.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    @Test
    public void testGrouppingBy3() throws Exception {
        Collector<Person, ?, List<String>> mapping = Collectors.mapping((s) -> s.getFirstName(), Collectors.toList());
        Map<String, List<String>> collect = javaProgrammers.stream().collect(Collectors.groupingBy((s) -> s.getGender(), TreeMap::new, mapping));
        System.out.println("collect = " + collect.getClass());
        collect.forEach((k, v) -> System.out.println(k + " = " + v));
    }


    @Test
    public void testMapping1() throws Exception {
        Collector<Person, ?, List<String>> mapping = Collectors.mapping((s) -> s.getFirstName(), Collectors.toList());
        Map<String, List<String>> collect = javaProgrammers.stream().collect(Collectors.groupingBy((s) -> s.getGender(), mapping));
        collect.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    @Test
    public void testReducing() throws Exception {
        Map<String, Integer> collect = javaProgrammers.stream().collect(Collectors.groupingBy((s) -> s.getGender(), Collectors.reducing(0, Person::getAge, Integer::sum)));
        collect.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
