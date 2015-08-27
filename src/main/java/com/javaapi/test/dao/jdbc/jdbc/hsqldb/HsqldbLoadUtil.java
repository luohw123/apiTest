package com.javaapi.test.dao.jdbc.jdbc.hsqldb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 15/8/27.
 */
public class HsqldbLoadUtil {
        private static SqlSessionFactory sqlSessionFactory;

        static {
            try {
                //创建SqlSessionFactory
                String resource = HsqldbLoadUtil.class.getResource("mybatis-java.xml").getPath();
                String createSql = HsqldbLoadUtil.class.getResource("CreateDB.sql").getPath();
                Reader reader = new InputStreamReader(new FileInputStream(resource));
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                reader.close();
                //创建数据库
                SqlSession session = null;
                try {
                    session = sqlSessionFactory.openSession();

                    Connection conn = session.getConnection();
                    reader = new InputStreamReader(new FileInputStream(createSql));
                    ScriptRunner runner = new ScriptRunner(conn);
                    runner.setLogWriter(null);
                    runner.runScript(reader);
                    reader.close();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取Session
         * @return
         */
        public static SqlSession getSqlSession(){
            return sqlSessionFactory.openSession();
        }
    @Test
    public void test() {
        SqlSession sqlSession = getSqlSession();
        List<Map<String,String>> list = sqlSession.selectList("testNamespace.select");
        System.out.println("list = " + list);
    }

}
