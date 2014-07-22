package com.javaapi.test.dao.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class MyTypeHandler extends BaseTypeHandler<TestTypeHandler> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			TestTypeHandler parameter, JdbcType jdbcType) throws SQLException {
		String address = parameter.getPhone() + "@" + parameter.getEmail();
		ps.setString(i, address);
	}

	@Override
	public TestTypeHandler getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return dealType(rs.getString(columnName));
	}



	@Override
	public TestTypeHandler getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return dealType(rs.getString(columnIndex));
	}

	@Override
	public TestTypeHandler getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		return dealType(cs.getString(columnIndex));
	}

	private TestTypeHandler dealType(String result) {
		String phone = result.split("@")[0];
		String email = result.split("@")[1];
		TestTypeHandler testTypeHandler = new TestTypeHandler();
		testTypeHandler.setEmail(email);
		testTypeHandler.setPhone(phone);
		return testTypeHandler;
	}
}