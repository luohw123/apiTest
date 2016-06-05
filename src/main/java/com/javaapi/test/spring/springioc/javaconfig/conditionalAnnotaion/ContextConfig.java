package com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion;

import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion.Service.CustomerService;
import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion.Service.impl.CustomerServiceImpl1;
import com.javaapi.test.spring.springioc.javaconfig.conditionalAnnotaion.Service.impl.CustomerServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class ContextConfig {

    @Bean
    @ConditionalOnSystemProperty("servicedefault")
    public CustomerService service1() {
        return new CustomerServiceImpl1();
    }

    @Bean
    @ConditionalOnSystemProperty(value="servicedefault", exists=false)
    public CustomerService service2() {
        return new CustomerServiceImpl2();
    }
}
