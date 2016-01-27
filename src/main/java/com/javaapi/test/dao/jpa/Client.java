package com.javaapi.test.dao.jpa;

import com.javaapi.test.dao.jpa.dao.UserDao;
import com.javaapi.test.dao.jpa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 16/1/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@ActiveProfiles("development")
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
    public void testUpdate() throws Exception {

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