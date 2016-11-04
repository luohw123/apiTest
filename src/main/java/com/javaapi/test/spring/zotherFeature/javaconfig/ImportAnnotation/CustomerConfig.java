package com.javaapi.test.spring.zotherFeature.javaconfig.ImportAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean(name="customer")
    public CustomerBo customerBo(){

        return new CustomerBo();

    }
}