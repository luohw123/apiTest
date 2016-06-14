package com.javaapi.test.dao.jdbc.transaction.transactiondeclare.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通过annotation实现事务
 * 1 http://sishuok.com/forum/blogPost/list/2506.html
 * 2 事务配置的5种方式
 *
 * ==============
 * 注解使用InfrastructureAdvisorAutoProxyCreator 进行事物的自动代理
 * 使用BeanFactoryTransactionAttributeSourceAdvisor作为advisor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
    @Autowired
    IupdateService iupdateService;
    
    @Test
//    @Transactional
    public void testUpdate() {
        iupdateService.update();
    }
}
