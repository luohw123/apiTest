package com.javaapi.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public static void commit(Connection con) {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackSavePoint(Connection con, Savepoint sp) {
        try {
            if (con != null) {
                con.rollback(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * reset autocommit to
     * 
     * @param con
     */

    public static void resetConnection(Connection con) {
        try {
            if (con != null) {
                if (con.getAutoCommit()) {
                    con.setAutoCommit(false);
                } else {
                    con.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    public static void close(Connection con, PreparedStatement pstmt,
            ResultSet rs) {
        close(rs);
        close(pstmt);
        close(con);
    }
}
