package com.javaapi.test.dao.mybatis.springInterface.optimisticLock;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 
 * CREATE TABLE `cash_book` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `account_id` bigint(20) DEFAULT NULL COMMENT 'account表id',
  `balance` decimal(12,2) DEFAULT NULL COMMENT '余额',
  `version` decimal(12,2) DEFAULT NULL COMMENT '乐观锁版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现金表';

 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@Transactional
public class Client{

	@Autowired
	SqlSessionTemplate	sqlSessionTemplate;
	
	@Autowired
	CashBookService cashBookService;
	@Autowired
	CashBookForUpdateService cashBookForUpdateService;
	
	@Test
	public void selectOne() {
		CashBook cash  = new CashBook();
		cash.setAccountId(1l);
		CashBook seletOne = sqlSessionTemplate.getMapper(CashBookDao.class).selectOne(cash);
		System.out.println(seletOne);
	}
	@Test
	@Rollback(value=false)
	public void update() {
		CashBook cash  = new CashBook();
		cash.setAccountId(1l);
		cash.setBalance(BigDecimal.valueOf(1l));
		cash.setVersion(BigDecimal.valueOf(0l));
		int result = sqlSessionTemplate.getMapper(CashBookDao.class).updateBalance(cash);
		Assert.assertEquals(1, result);
	}
	@Test
	@Rollback(value=false)
	public void testSelectForUpdate() throws Exception {
		cashBookService.addBalance(1l, BigDecimal.valueOf(1l));
	}
	
	/**
	 * 乐观锁，10次测试:并发5个线程，重试5次，5成功.
	 * 乐观锁，10次测试:并发10个线程，重试5次，8-10成功.
	 * 乐观锁，10次测试:并发50个线程，重试5次，10-25成功.
	 * 乐观锁，10次测试:并发100个线程，重试5次，20~38成功.</br>
	 * next 相应时间?//TODO
	 * 统一异常处理//TODO</br>
	 */
	@Test
	@Rollback(value=false)
	public void testConcurrence() throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cashBookService.addBalance(1l, BigDecimal.valueOf(1l));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		TimeUnit.HOURS.sleep(1);
	}
	/**
	 * 注意一定要加上事务
	 */
	@Test
	@Rollback(value=false)
	public void testForUpdate() throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cashBookForUpdateService.addBalance(1l, BigDecimal.valueOf(1l));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
		System.out.println("---------");
//		TimeUnit.HOURS.sleep(1);
	}
}
