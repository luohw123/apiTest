package com.javaapi.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.javaapi.test.freemarker.FreeMarkerUtil;

public class JdbcTest {
    // ------------------
    private static final String NAME         = "name";
    private static final String LEAGUELIB789 = "leaguelib789";
    private static final String LEAGUELIB    = "leaguelib";
    private static final String leaguePath   = "jdbc:mysql://192.168.66.13:3306/leaguelib?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";

    // ------------------

    @Before
    public void prepare() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jdbc() {
        String path = leaguePath;
        String username = LEAGUELIB;
        String password = LEAGUELIB789;
        String colomun = NAME;
        String sql = "SELECT * FROM leaguelib.league limit 0,1000;";
        String sqlString = "SELECT * FROM bk_league where bk_league.name not in ("
                + "SELECT  league.name_cn FROM league inner join bk_league ON league.name_cn = bk_league.name);";
        try {
            Connection con = getCon(path, username, password);
            PreparedStatement ps = con.prepareStatement(sqlString);
            ps.execute();
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                dealResult(rs, colomun);
            } else {
                System.out.println("失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dealResult(ResultSet rs, String colomun) {
        List<String> list = new ArrayList<String>();
        try {
            while (rs.next()) {
                System.out.println(rs.getString(colomun));
                list.add(rs.getString(colomun));
            }

            String freemarkerName = "test.ftl";
            String property = "testProperty.ftl";
            String freemarkerHtml = "test.shtml";
            String freemarkerProperty = "test.properties";
            String path2 = JdbcTest.class.getResource("").getPath();
            System.out.println(path2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", list);
            FreeMarkerUtil.geneHtmlFile(freemarkerName, map, path2,
                    freemarkerHtml);
            FreeMarkerUtil.geneHtmlFile(property, map, path2,
                    freemarkerProperty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getCon(String path, String username, String password)
            throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(path, username, password);
        return con;
    }
}
