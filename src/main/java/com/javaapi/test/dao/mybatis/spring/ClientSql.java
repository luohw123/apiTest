package com.javaapi.test.dao.mybatis.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.javaapi.test.dao.mybatis.Social;
import com.zeroturnaround.javarebel.se;

/**
 * http://mybatis.github.io/mybatis-3/zh/dynamic-sql.html
 * CREATE TABLE `tbl_b` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@Transactional
public class ClientSql {

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;

	/**
	 * where 只能处理and 或or 在前面得
	 */
	@Test
	public void testWhereBefore() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		List<Map<String,String>> selectList = sqlSessionTemplate.selectList("testSqlNamespace.selectWhereAndBefore",map);
		for (Map<String, String> tmp : selectList) {
			System.out.println(tmp);
		}
	}
	
	@Test
	public void testWhereAfter() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		List<Map<String,String>> selectList = sqlSessionTemplate.selectList("testSqlNamespace.selectWhereAndAfter",map);
		for (Map<String, String> tmp : selectList) {
			System.out.println(tmp);
		}
	}
	/**
	 * set 标签不能处理逗号在前
	 */
	@Test
	public void testUpdateSetBefore() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		int update = sqlSessionTemplate.update("testSqlNamespace.updateSetBefore", map);
	}
	/**
	 * set 只能处理,逗号在后面得
	 */
	@Test
	public void testUpdateSetAfter() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		int update = sqlSessionTemplate.update("testSqlNamespace.updateSetAfter", map);
	}
	@Test
	public void testSelectTrimAndBefore() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		sqlSessionTemplate.selectList("testSqlNamespace.selectTrimAndBefore", map);
	}
	@Test
	public void testUpdateTrimAfter() {
		Map<String,String> map = new HashMap<>();
		map.put("param1", "1");
		int update = sqlSessionTemplate.update("testSqlNamespace.updateTrimAfter", map);
	}
//	------------
	@Test
	public void testSelectForEachList() {
		List<Integer> tmpList = new ArrayList<>();
		tmpList.add(1);
		tmpList.add(2);
		tmpList.add(3);
		sqlSessionTemplate.selectList("testSqlNamespace.selectForeachList", tmpList);
	}
	@Test
	public void testSelectForEachMap() {
		List<Integer> tmpList = new ArrayList<>();
		tmpList.add(1);
		tmpList.add(2);
		tmpList.add(3);
		Map<String,Object> map = new HashMap<>();
		map.put("ids", tmpList);
		List<Social> selectList = sqlSessionTemplate.selectList("testSqlNamespace.selectForeachMap", map);
		System.out.println(selectList);
	}
	/**
	 * 暂时没测好
	 */
	@Test
	public void testSelectForEachMap2() {
		Map<String,Object> map = new HashMap<>();
		map.put("1", 1);
		map.put("1", 2);
		map.put("1", 3);
		List<Social> selectList = sqlSessionTemplate.selectList("testSqlNamespace.selectForeachMap2", map);
		System.out.println(selectList);
	}
	/**mybatis 原生SqlSession支持 
	 * 测试返回Map<指定字段, 实体> 
	 */
	@Test
	public void testSelectForReturnMap() {
		Map<String,Object> map = new HashMap<>();
		Map<Integer, Social> selectMap = sqlSessionTemplate.selectMap("testSqlNamespace.selectReturnMap",map,"id");
		System.out.println(selectMap);
		System.out.println(selectMap.get(1));
	}
}
