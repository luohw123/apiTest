package com.javaapi.test.test.testJavaFeature.Version_1_8.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
//import java.util.function.Consumer;
//import java.util.function.DoubleSupplier;
//import java.util.function.Predicate;
//
//import static java.util.stream.Collectors.*;

/**
 *    http://blog.csdn.net/dm_vincent/article/details/40340291
 */
public class Client2 {
    //
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
                add(new Person("Evonne", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
    }

    /**
     * lambda的重用
     */
    @Test
    public void test() {
        final Predicate<Person> startsWithN = p -> p.getFirstName().startsWith("J");
        final Predicate<Person> startsWithB = p -> p.getFirstName().startsWith("C");

        final long countFriendsStartN = javaProgrammers.stream().filter(startsWithN).count();
        final long countFriendsStartB = javaProgrammers.stream().filter(startsWithB).count();
        System.out.println(countFriendsStartN);
        System.out.println(countFriendsStartB);
        //-----------------------
        final long countFriendsStartJ = javaProgrammers.stream().filter(checkIfStartsWith("J")).count();
        final long countFriendsStartC = javaProgrammers.stream().filter(checkIfStartsWith("C")).count();
        //-------------------------
        final Function<String, Predicate<Person>> startsWithLetter =
                (String letter) -> {
                    Predicate<Person> checkStartsWith = (Person name) -> name.getFirstName().startsWith(letter);
                    return checkStartsWith;
                };

        final Function<String, Predicate<Person>> startsWithLetterBest = (String letter) -> (Person name) -> name.getFirstName().startsWith(letter);
        final Function<String, Predicate<Person>> startsWithLette3 = letter -> name -> name.getFirstName().startsWith(letter);

        final long countFriendsStartJ_best = javaProgrammers.stream().filter(startsWithLetterBest.apply("J")).count();
        System.out.println("countFriendsStartJ_best = " + countFriendsStartJ_best);

    }

    public static Predicate<Person> checkIfStartsWith(final String letter) {
        return p -> p.getFirstName().startsWith(letter);
    }

    /**
     * optinal
     */
    @Test
    public void testOptinal() throws Exception {
        List<String> collect = javaProgrammers.stream().map((person -> {
            return person.getFirstName();
        })).collect(toList());
        String sindyNew = pickName(collect, "Sindy");
        System.out.println("sindy = " + sindyNew);
        //---
        String sindy = pickName(collect, "11");
        System.out.println("sindy = " + sindy);

    }

    public static String pickName(
            final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();
//        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
        return foundName.isPresent() ? foundName.get():foundName.orElse("not found");
    }

    @Test
    public void testToMap() throws Exception {
        long start = System.currentTimeMillis();
        Map<String, Person> collect = phpProgrammers.stream().collect(Collectors.toMap((p) -> p.getFirstName(), (p) -> p, (oldVal, newVal) -> newVal));
        long end = System.currentTimeMillis();
        System.out.println("(end-start) = " + (end - start));
    }
    @Test
    public void testToMap2() throws Exception {
        long start = System.currentTimeMillis();
        Map<String, Person> map = new HashMap<>();
        for (Person phpProgrammer : phpProgrammers) {
            map.put(phpProgrammer.getFirstName(), phpProgrammer);
        }
        long end = System.currentTimeMillis();
        System.out.println("(end-start) = " + (end - start));
    }

    @Test
    public void testReduce() throws Exception {
        int sum = javaProgrammers.stream()
                .mapToInt(p -> p.getFirstName().length())
                .sum();
        System.out.println("Total number of characters in all names: " +
                sum);

    }

    /**
     * reduce
     * 在对一个集合中的元素按照顺序进行两两操作时，根据某种策略来得到一个结果，得到的结果将作为一个元素参与到下一次操作中，最终这个集合会被归约成为一个结果。这个结果也就是reduce方法的返回值。<br>
     *     第一次执行两两操作时，name1和name2代表的是集合中的第一个和第二个元素，当第一个元素的长度大于等于第二个元素时，将第一个元素保留下来，否则保留第二个元素。 第二次执行两两操作时，name1代表的是上一次操作中被保留下来的拥有较长长度的元素，name2代表的是第三个元素。 以此类推...最后得到的结果就是集合中第一个拥有最长长度的元素了。<br>
     *     reduce方法返回的对象类型时Optional，这是因为待操作的集合可能是空的。<br>
     *         reduce方法是会按照集合的顺序对其元素进行两两操作的，可以额外传入一个值作为“基础值”或者“默认值”，那么在第一次进行两两操作时，第一个操作对象就是这个额外传入的值，第二个操作对象是集合中的第一个元素<br>

     */
    @Test
    public void testReduce2() throws Exception {
        final Optional<Person> aLongName = javaProgrammers.stream()
                .reduce((person1, person2) ->
                        person1.getFirstName().length() >= person2.getFirstName().length() ? person1 : person2);
        aLongName.ifPresent(person -> System.out.println(String.format("A longest name: %s", person.getFirstName())));
    }

    @Test
    public void testName() throws Exception {
        String collect = javaProgrammers.stream().map(p -> p.getFirstName().toUpperCase()).collect(Collectors.joining(", "));
        System.out.println("collect = " + collect);
    }

    /**
     * map reduce
     * http://blog.csdn.net/dm_vincent/article/details/40856569
     * @throws Exception
     */
    @Test
    public void testMapReduce() throws Exception {

    }
}
