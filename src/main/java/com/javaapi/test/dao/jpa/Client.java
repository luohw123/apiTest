package com.javaapi.test.dao.jpa;

import com.javaapi.test.dao.jpa.dao.UserDao;
import com.javaapi.test.dao.jpa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 16/1/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@ActiveProfiles("development")
@Transactional
public class Client {
    @Autowired
    private UserDao dao;

    @Test
    public void testInsert() throws Exception {

        User s = new User();
        s.setUsername("nihao");
        s.setPassword("nihao");
        User save = dao.save(s);
        System.out.println("save = " + save);
    }
    @Test
    @Rollback(value = false)
    // 非显示更新
    public void testUpdate() throws Exception {
        User one = dao.findOne(1);
        one.setUsername("kk2");
//        dao.save(one);
        System.out.println("one = " + one);

    }

    @Test
    public void testExist() throws Exception {
        User one = dao.findOne(1);
        System.out.println("dao = " + dao.exists(one.getId()));

    }



    @Test
    public void testCount() throws Exception {
        System.out.println("dao = " + dao.count());
    }

    @Test
    public void testPage() throws Exception {
        Page<User> all = dao.findAll(new PageRequest(0, 1));
        System.out.println(all.getContent());
        System.out.println("all = " + all);
    }

    @Test
    public void testFindOne() throws Exception {
        User findOne = dao.findOne(1);
        System.out.println(findOne);
        assertNotNull(findOne);
    }
    @Test
    public void findOne() throws Exception {
        User user = dao.findUserByQueryAnnotation("admin");
        System.out.println(user);
        assertNotNull(user);
    }
}