package com.javaapi.test.dao.jdbc.springjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试 spring jdbc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	JdbcTemplate	jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate nameJdbc;

	@SuppressWarnings("unchecked")
	@Test
	public void testJdbcTemplateSelect() {
		final String select = "select * from csc_sns_dev.tbl_b where val=?";
//		jdbcTemplate.query(select, null);
		jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				con.prepareStatement(select, new String[]{});
				return null;
			}
		}, new RowMapper<Map<String,String>>() {

			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return null;
			}
			
		} );
		
	}
	@Test
	public void testJdbcTemplateUpdate() {
		String id = "1";
		String sql = "update csc_sns_dev.tbl_b set val='kk'  where id=" + id;
		int result = jdbcTemplate.update(sql);
		System.out.println(result);
	}
	/**
	 * spring 提供得NamedParameterJdbcTemplate,支持命名参数绑定
	 */
	@Test
	public void testNamedJdbcTemplateUpdate() {
		String sql = "select count(1) from csc_sns_dev.tbl_b  where id=:id";
		Map<String,String> map =new HashMap<>();
		map.put("id", "1");
		int result = nameJdbc.queryForInt(sql, map);
		System.out.println(result);
	}
}
