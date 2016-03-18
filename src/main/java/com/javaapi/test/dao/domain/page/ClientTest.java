package com.javaapi.test.dao.domain.page;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
public class ClientTest {
    @Test
    public void test() {
        // select count(1) from xxx;
        int totalCount  = 100;  //from dao

        Pagination partData = getPartData(new Pagination(1, 10, totalCount));
        List<?> list = partData.getList();
        System.out.println(list);


    }


    private Pagination getPartData(Pagination pagination) {
        int firstResult = pagination.getFirstResult();
        int totalCount = pagination.getTotalCount();
        //sql  select * from xxx  limit firstResult, totalCount;
        pagination.setList(new ArrayList());
        return pagination;
    }
}

