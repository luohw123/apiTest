package com.javaapi.test.jdbc.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 1 spring 事务
 * 2 PlatformTransactionManager 
 * http://blog.csdn.net/qqqrui/article/details/12910005
 * http://www.iteye.com/topic/480432
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	DataSourceTransactionManager dataSourceTransactionManager; 
	 private static DefaultTransactionDefinition def = new DefaultTransactionDefinition();  ;  
	 static{
		  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);  
	 }
	@Test
	public void testProgramTransaction(){
//		JdbcTemplate 	jdbcTemplate = new JdbcTemplate(dataSource); 
		TransactionStatus status =   dataSourceTransactionManager.getTransaction(def);
		// 到底是jdbcTemplate还是普通connection?
//		  dataSourceTransactionManager.rollback(status);  
		dataSourceTransactionManager.commit(status);
	}
}
