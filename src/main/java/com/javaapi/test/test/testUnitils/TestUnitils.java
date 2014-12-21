package com.javaapi.test.test.testUnitils;

import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.Arrays;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionComparatorMode;

/**
 * unitils本身提供了很多断言的支持:http://stamen.iteye.com/blog/1480316</br>
 * 也可以集成spring,但是跟单独使用spring-test测试框架没多什么功能 - -</br> 集成dbunit</br>
 * 
 */
public class TestUnitils {

	@Test
	public void testassertReflectionEquals() {
		// Exact field-by-field comparison
		assertReflectionEquals(new Person("John", "Doe", new Address("New street", 5, "Brussels")), 
 new Person("John", "Doe",
				new Address("New street", 5, "Brussels")));
	}

	@Test
	public void testReflectionComparatorModeIGNORE_DEFAULTS() {
		// Ignore Null / 0 values in the expected object
		Person expected = new Person("John", null, new Address("New street", 0,
				null));
		Person actual = new Person("John", "Doe", new Address("New street", 5,
				"Brussels"));
		assertReflectionEquals(expected, actual,
				ReflectionComparatorMode.IGNORE_DEFAULTS);
	}

	@Test
	public void testOrder() {
		// Ignore collection order
		assertReflectionEquals(Arrays.asList(new Person("John"), new Person("Jane")),
		                                 new Person[] {new Person("Jane"), new Person("John")}, 
		                                 ReflectionComparatorMode.LENIENT_ORDER);
	}

	@Test
	public void testIgnoreProperty() {
		// Ignore null/0 values + collection order
		assertLenientEquals(Arrays.asList(new Person("John"), null),
				new Person[] { new Person("Jane", "Doe"),
						new Person("John", "Doe") });
	}

	@Test
	public void testassertPropertyLenientEquals() {
		// Check only the firstName property 
		assertPropertyLenientEquals("firstName", Arrays.asList("John", "Jane"),
				new Person[] { new Person("Jane", "Doe"),
						new Person("John", "Doe") });
	}

}
