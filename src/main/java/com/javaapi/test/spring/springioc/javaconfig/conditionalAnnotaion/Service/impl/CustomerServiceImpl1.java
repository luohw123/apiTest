package com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion.Service.impl;

import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion.Service.CustomerService;

/**
 * Created by user on 16/6/5.
 */
public class CustomerServiceImpl1 implements CustomerService {
    @Override
    public void print() {
        System.out.println("1 = " + 1);
    }
}
