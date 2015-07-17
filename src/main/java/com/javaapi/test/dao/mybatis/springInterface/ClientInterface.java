package com.javaapi.test.dao.mybatis.springInterface;

import java.util.HashMap;
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
		System.err.println(countUser);
		Assert.assertEquals(0, countUser);
	}
	/**
	 * 测试对billid为数字类型时候,xml里有判断 billid!=null, 或者billid!='' 时候是否会报错.
	 * 测试结果是会报错
	 */
	@Test
	public void selectParam() {
		 Map<String, String> selectBill = sqlSessionTemplate.getMapper(SocialMapper.class).selectBill(58l);
		 System.err.println(selectBill);
	}
	/**
	 * 测试对billid为数字类型时候,放入map,xml里判断 billid!=null, 或者billid!='' 时候是否会报错.
	 * 测试结果是不会报错
	 */
	@Test
	public void selectParamMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("billid", 58l);
		Map<String, String> selectBill = sqlSessionTemplate.getMapper(SocialMapper.class).selectBillMap(map);
		System.err.println(selectBill);
	}
	
	@Test
	public void selectBillAll() {
		List<Map<String, String>> countUser = sqlSessionTemplate.getMapper(BillMapper.class).selectAll();
		System.err.println(countUser);
	}
}
