package com.javaapi.test.dao.mybatis;

public class User {

    private Integer id;
    private String  name;
    
	private TestTypeHandler	testTypeHandler;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public TestTypeHandler getTestTypeHandler() {
		return testTypeHandler;
	}

	public void setTestTypeHandler(TestTypeHandler testTypeHandler) {
		this.testTypeHandler = testTypeHandler;
	}

}