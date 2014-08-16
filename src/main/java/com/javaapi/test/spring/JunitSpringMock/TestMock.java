package com.javaapi.test.spring.JunitSpringMock;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
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
