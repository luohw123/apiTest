package com.javaapi.test.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 调用存储果过程
 *
 */
public class TestCallPROCEDURE {

	private static final String	url			= "jdbc:mysql://127.0.0.1:3306/csc_sns_dev?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";
	private static final String	username	= "root";
	private static final String	password	= "root";

	/**
	 * 1 CREATE PROCEDURE usp1(IN p INT) SET @x = p ;</br>
	 * 同一连接内有效
	 */
	@Test
	public void testProceduer() {
		Connection con = JdbcPrepare.getConnection(url, username, password);
		CallableStatement call = null;
		ResultSet rs = null;
		String sql = "call usp2(?);";
		String sql2 = "select @x";
		try {
			call = con.prepareCall(sql);
			call.setString(1, "1234563");
			rs = call.executeQuery();
			PreparedStatement prepareStatement = con.prepareStatement(sql2);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				String col = executeQuery.getString(1);
				System.out.println(col);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcAfter.close(con, call, rs);
		}
	}
	/**
	 * CREATE PROCEDURE usp1(IN p INT) SET @x = p ;
	 */
	@Test
	public void testCreateProceduer() {
		Connection con = JdbcPrepare.getConnection(url, username, password);
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "CREATE PROCEDURE usp3(OUT  inoutParam INT) SET inoutParam = '12347'";
		try {
			ps = con.prepareStatement(sql);
			int executeUpdate = ps.executeUpdate();
			System.out.println(executeUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcAfter.close(con, ps, rs);
		}
	}
	@Test
	public void testInvokeOutParam() {
		Connection con = JdbcPrepare.getConnection(url, username, password);
		CallableStatement call = null;
		ResultSet rs = null;
		String sql = "call usp2(?);";
		try {
			call = con.prepareCall(sql);
			call.registerOutParameter(1, java.sql.Types.VARCHAR);
			rs = call.executeQuery();
			while (rs.next()) {
				String string = rs.getString(1);
				System.out.println(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcAfter.close(con, call, rs);
		}
	
	}
}
