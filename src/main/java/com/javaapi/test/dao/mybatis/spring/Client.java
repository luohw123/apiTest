package com.javaapi.test.dao.mybatis.spring;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaapi.test.dao.mybatis.Social;
import com.javaapi.test.dao.mybatis.TestTypeHandler;
import com.javaapi.test.dao.mybatis.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
// @Transactional
public class Client {

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;

	@Test
	public void selectOne() {
		String selectOne = "testNamespace.countUser";
		Integer a = sqlSessionTemplate.selectOne(selectOne);
		System.out.println(a);
	}

	@Test
	public void selectOneObjectResultType() {
		String selectOne = "testNamespace.getTop1User";
		Social a = sqlSessionTemplate.selectOne(selectOne);
		System.out.println(a);

		List<Social> list = sqlSessionTemplate.selectList(selectOne);
		System.out.println(list);
	}

	@Test
	public void selectOneObjectResultMap() {
		String selectOne = "testNamespace.getTop1UserResultMap";
		Social a = sqlSessionTemplate.selectOne(selectOne);
		System.out.println(a);
	}

	@Test
	public void selectListObject() {
		String selectOne = "testNamespace.getTop1UserResultMap";
		List<Social> a = sqlSessionTemplate.selectList(selectOne);
		System.out.println(a);
	}

	@Test
	public void insertTypeHandler() {
		String insertOne = "testNamespace.insertUserTypeHandler";
		User user = new User();
		user.setName("kk");
		TestTypeHandler testTypeHandler = new TestTypeHandler();
		testTypeHandler.setPhone("12312312312");
		testTypeHandler.setEmail("163.com");
		user.setTestTypeHandler(testTypeHandler);
		int a = sqlSessionTemplate.insert(insertOne, user);
		System.out.println(a);
		System.out.println(user.getId());
		// String getOneUser = "testNamespace.getOneUser";
		// sqlSessionTemplate.selectOne(getOneUser, user);
	}

	@Test
	public void selectTypeHandler() {
		User user = new User();
		user.setId(Integer.parseInt("6"));
		String getOneUser = "testNamespace.getOneUser";
		User myUser = sqlSessionTemplate.selectOne(getOneUser, user);
		System.out.println(myUser.getTestTypeHandler().getPhone());
		System.out.println(myUser.getTestTypeHandler().getEmail());
	}
}