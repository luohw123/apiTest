package com.javaapi.test.dao.hibernate.biz.usertype.one2manydanxiang_xml;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class BillDesc implements UserType {
	private String name = "";
	  private   static   final   int [] TYPES  =   new   int [] {Types.VARCHAR};
	@Override
	public int[] sqlTypes() {
		return TYPES;
	}

	@Override
	public Class<BillDesc> returnedClass() {
		return BillDesc.class;
	}

	@Override
	public boolean equals(Object obj, Object obj1) throws HibernateException {
		return false;
	}

	@Override
	public int hashCode(Object obj) throws HibernateException {
		return 0;
	}

	@Override
	public Object nullSafeGet(ResultSet resultset, String[] as,
			SessionImplementor sessionimplementor, Object obj)
			throws HibernateException, SQLException {
		BillDesc billDesc = new BillDesc();
		billDesc.setName("kkSelfConfig");
		return billDesc;
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedstatement, Object obj,
			int i, SessionImplementor sessionimplementor)
			throws HibernateException, SQLException {
		
	}

	@Override
	public Object deepCopy(Object obj) throws HibernateException {
		BillDesc billDesc = new BillDesc();
		billDesc.setName("kkSelfConfig");
		return billDesc;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Serializable disassemble(Object obj) throws HibernateException {
		return null;
	}

	@Override
	public Object assemble(Serializable serializable, Object obj)
			throws HibernateException {
		return null;
	}

	@Override
	public Object replace(Object obj, Object obj1, Object obj2)
			throws HibernateException {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
