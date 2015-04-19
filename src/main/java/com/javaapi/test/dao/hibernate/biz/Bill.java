package com.javaapi.test.dao.hibernate.biz;

import java.io.Serializable;

public class Bill implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				id;
	private String				billname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillname() {
		return billname;
	}

	public void setBillname(String billname) {
		this.billname = billname;
	}

}
