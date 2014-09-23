package com.javaapi.test.dao.jdbc.transactiondeclare.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通过aop实现事物
 * 1 http://sishuok.com/forum/blogPost/list/2506.html
 * 2 事务配置的5种方式
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {

    //	@Autowired
    //	IinsertService  insertService;

    @Autowired
    IupdateService iupdateService;
	
    //	@Test
    //	public void test(){
    //		insertService.insert();
    //	}

    @Test
    public void testUpdate() {
        iupdateService.update();
    }
}
