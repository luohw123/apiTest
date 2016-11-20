package com.javaapi.test.dao.domain.page;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
public class ClientTest {
    @Test
    public void testPageNo0() {
        // select count(1) from xxx;
        int totalCount  = 100;  //from dao

        Pagination<String> partData = getPartData(new Pagination(0, 10, totalCount));
        List<String> list = partData.getList();
        System.out.println("list = " + list);
    }

    @Test
    public void testPageNo1() {
        // select count(1) from xxx;
        int totalCount  = 100;  //from dao

        Pagination<String> partData = getPartData(new Pagination(1, 10, totalCount));
        List<String> list = partData.getList();
        System.out.println("list = " + list);
    }

    @Test
    public void testPageNo2() {
        // select count(1) from xxx;
        int totalCount  = 100;  //from dao

        Pagination<String> partData = getPartData(new Pagination(2, 10, totalCount));
        List<String> list = partData.getList();
        System.out.println(list);

    }
    @Test
    public void testPageNo3() {
        // select count(1) from xxx;
        int totalCount  = 100;  //from dao

        Pagination<String> partData = getPartData(new Pagination(2, 10, totalCount));
        List<String> list = partData.getList();
        System.out.println(list);

    }


    private <T> Pagination<T> getPartData(Pagination pagination) {
        int firstResult = pagination.getFirstResult();
        System.out.println("first = " + firstResult);
        int totalPage = pagination.getTotalPage();
        int pageSize = pagination.getPageSize();
        System.out.println("pageSize = "+pageSize);
        System.out.println("totalPage = "+totalPage);
        //sql  select * from xxx  order by yyy limit firstResult, pageSize;
        ArrayList<String> list = new ArrayList<>();
        list.add("nihao");
        list.add("nihao2");
        pagination.setList(list);
        return pagination;
    }
}

