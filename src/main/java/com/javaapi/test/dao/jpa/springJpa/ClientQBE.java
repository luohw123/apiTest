package com.javaapi.test.dao.jpa.springJpa;

import com.javaapi.test.dao.jpa.springJpa.dao.UserDaoQBE;
import com.javaapi.test.dao.jpa.springJpa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 1  可以实现单表动态查询
 * 2  更复杂的查询需要再探索下example
 * 3 另外 spring data jpa 新版本中,自己的接口只要继承JpaRepository,自带qbe功能了
 * 4 看queryDsl的实现类的diagram就能看到jpa的所有查询
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@ActiveProfiles("development")
//@Transactional
public class ClientQBE {


    @Autowired
    private UserDaoQBE userDaoQBE;

    @Test
    public void testFindOne() throws Exception {
        User user = new User();
        user.setPassword("nihaoppp");
        User one = userDaoQBE.findOne(Example.of(user));
        System.out.println("one = " + one);
    }

    @Test
    public void testFindAll() throws Exception {
        User user = new User();
        user.setPassword("nihao");
        List<User> all = userDaoQBE.findAll(Example.of(user));
        all.stream().forEach((s)->{
            System.out.println(s);
        });
    }
}