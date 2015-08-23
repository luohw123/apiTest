package com.javaapi.test.application.cache.redis;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Date  createTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	
}
