package com.javaapi.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void executeQuery(Connection con, String dbName)
            throws SQLException {
        PreparedStatement stmt = null;
        String query = "select COF_NAME, SUP_ID, PRICE, " + "SALES, TOTAL "
                + "from " + dbName + ".COFFEES";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID + "\t"
                        + price + "\t" + sales + "\t" + total);
            }
        } catch (SQLException e) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void executeUpdate(Connection con, String dbName)
            throws SQLException {
        PreparedStatement stmt = null;
        String query = "select COF_NAME, SUP_ID, PRICE, " + "SALES, TOTAL "
                + "from " + dbName + ".COFFEES";
        try {
            stmt = con.prepareStatement(query);
            int rs = stmt.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void execute(Connection con, String dbName)
            throws SQLException {
        PreparedStatement stmt = null;
        String query = "select COF_NAME, SUP_ID, PRICE, " + "SALES, TOTAL "
                + "from " + dbName + ".COFFEES";
        try {
            stmt = con.prepareStatement(query);
            boolean rs = stmt.execute();
            System.out.println(rs);
            stmt.getResultSet();// ?
            stmt.getUpdateCount(); // ?
        } catch (SQLException e) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
