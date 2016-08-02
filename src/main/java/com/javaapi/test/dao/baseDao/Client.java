package com.javaapi.test.dao.baseDao;

import org.junit.Test;

/**
 * Created by user on 16/8/2.
 */
public class Client {

    private IBaseHqlDao<String,Integer> iBaseHqlDao;

    @Test
    public void test() {
        String one = iBaseHqlDao.findOne("");

    }


}
