package com.javaapi.test.spring.JunitSpring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 1 避免硬编码getBean</br>
 * 2 不会进行多次spring容器初始化-速度加快</br>
 * 3 数据库现场容易遭受破坏   ???</br>
 * 4 没有对数据操作正确性进行检查   ???</br>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class TestJunitSpring {
	@Autowired
	public School school;
	
	@Test
	public void test(){
		school.haveClass();
	}
}
