package com.javaapi.test.spring.JunitSpring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *继承自AbstractTransactionalJUnit4SpringContextTests后</br>
 *1 支持事务回滚</br>
 *2 支持数据库操作,从而对数据正确性进行验证</br>
 *
 *或者对 普通测试类进行 @Transactional 注解http://www.ibm.com/developerworks/cn/java/j-lo-springunitest/</br>
 *   @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback，即您不用自己清除自己所做的任何对数据库的变更了
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext-transaction.xml")
@Transactional
@TransactionConfiguration
public class JuintSpring3Transaction {
	@Autowired
	public JdbcTemplate	jdbcTemplate;
	
	@Test
	public void test(){
		String sql = "update csc_sns_dev.tbl_b set val='kk2' where id='2'";
		System.out.println("开始");
		jdbcTemplate.update(sql);
		System.out.println("结束");
	}
}
