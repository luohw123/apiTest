package com.javaapi.test.spring.JunitSpring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *继承自AbstractTransactionalJUnit4SpringContextTests后</br>
 *1 支持事务回滚</br>
 *2 支持数据库操作</br>
 *
 */
@ContextConfiguration("applicationContext.xml")
public class JuintSpring2 extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	public School school;
	
	@Test
	public void test(){
		
		school.haveClass();
	}
}
