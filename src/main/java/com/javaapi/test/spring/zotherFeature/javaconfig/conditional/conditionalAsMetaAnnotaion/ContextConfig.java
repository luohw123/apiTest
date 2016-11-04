package com.javaapi.test.spring.zotherFeature.javaconfig.conditional.conditionalAsMetaAnnotaion;

import com.javaapi.test.spring.zotherFeature.javaconfig.conditional.conditionalAsMetaAnnotaion.Service.CustomerService;
import com.javaapi.test.spring.zotherFeature.javaconfig.conditional.conditionalAsMetaAnnotaion.Service.impl.CustomerServiceImpl1;
import com.javaapi.test.spring.zotherFeature.javaconfig.conditional.conditionalAsMetaAnnotaion.Service.impl.CustomerServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class ContextConfig {

    @Bean
    @KKConditionalOnSystemProperty("servicedefault")
    public CustomerService service1() {
        return new CustomerServiceImpl1();
    }

    @Bean
    @KKConditionalOnSystemProperty(value="servicedefault", exists=false)
    public CustomerService service2() {
        return new CustomerServiceImpl2();
    }
}
