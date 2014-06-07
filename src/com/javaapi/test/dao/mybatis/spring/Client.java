package com.javaapi.test.dao.mybatis.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;

	@Test
	public void selectOne() {
		String selectOne = "testNamespace.countUser";
		Integer a = sqlSessionTemplate.selectOne(selectOne);
		System.out.println(a);
	}
}
