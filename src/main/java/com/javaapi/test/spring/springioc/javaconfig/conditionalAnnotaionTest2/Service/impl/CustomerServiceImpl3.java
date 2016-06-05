package com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaionTest2.Service.impl;

import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaionTest2.Service.CustomerService;

/**
 * Created by user on 16/6/5.
 */
public class CustomerServiceImpl3 implements CustomerService {
    @Override
    public void print() {
        System.out.println("system is  mac os");
    }
}
