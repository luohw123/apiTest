package com.javaapi.test.dao.mybatis.springInterface;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.javaapi.test.dao.mybatis.springInterface.mapper.BillMapper;
import com.javaapi.test.dao.mybatis.springInterface.mapper.SocialMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@Transactional
public class ClientInterface {

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;

	@Test
	public void selectOne() {
		int countUser = sqlSessionTemplate.getMapper(SocialMapper.class).countUser();
		Assert.assertEquals(0, countUser);
	}
	
	@Test
	public void selectBillAll() {
		List<Map<String, String>> countUser = sqlSessionTemplate.getMapper(BillMapper.class).selectAll();
		System.err.println(countUser);
	}
}
