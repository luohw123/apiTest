package com.javaapi.test.spring.springioc.javaconfig.enableAnnotation.Service;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ContextConfig.class)
@Documented
public @interface EnableKKContextConfig {
}
