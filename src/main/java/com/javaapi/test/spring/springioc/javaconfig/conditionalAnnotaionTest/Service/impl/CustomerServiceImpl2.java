package com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaionTest.Service.impl;

import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaionTest.Service.CustomerService;

/**
 * Created by user on 16/6/5.
 */
public class CustomerServiceImpl2 implements CustomerService {
    @Override
    public void print() {
        System.out.println("2 = " + 2);
    }
}
