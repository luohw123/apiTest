package com.javaapi.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcPrepare {

    public static PreparedStatement getPrepareStatement(Connection con,
            String sql) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

	/**
	 * getConnection
	 * 
	 * @return
	 */
	public static Connection getConnection(String url, String username,
	        String password) {
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url, username, password);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}

	/**
	 * .jdbc中的事务，只要设置是否自动提交即可 关闭自动提交 开启事务 开启自动提交，关闭事务
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void beginTransaction(Connection con) {
	
	    try {
	        if (con != null) {
	            if (con.getAutoCommit()) {
	                con.setAutoCommit(false);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	}
}
