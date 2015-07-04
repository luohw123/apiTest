package com.javaapi.test.application.test.testmockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;

/**
 * http://mockito.github.io/mockito/docs/current/org/mockito/Mockito.html<br>
 * 如何与Spring整合:https://bitbucket.org/kubek2k/springockito/wiki/Home</br>
 * 
 */
public class TestMockito {

	@Test
	public void test(){
		//mock creation
		List<String> mockedList = mock(List.class);
		//using mock object
		mockedList.add("one");
		mockedList.clear();
		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}
}
