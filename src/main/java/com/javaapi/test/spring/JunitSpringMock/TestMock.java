package com.javaapi.test.spring.JunitSpringMock;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 这种方式在单元测试中不生效
 * 
 */
@Component
@Deprecated
public class TestMock implements ServletContextAware {

    private ServletContext servletContext;

    public ServletContext getMockServlet() {
        return this.servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

}
