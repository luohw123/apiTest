package com.javaapi.test.test.testdbunit.baseSample;


import java.io.FileInputStream;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * http://blog.csdn.net/abing37/article/details/5019882</br>
 * 会按着memberSet里的数据初始化数据,也就是数据库里原有的数据都会被干掉</br> h2,hsqldb</br>
 * 
 * 
 */
public class MemberHibernateDaoTest extends DBTestCase {
 
         IMemberDao memberDao = new MemberHibernateDao();
 
//设置数据库DB Connection的相关信息，因为dbunit测试用例需要对数据库操作。
	public MemberHibernateDaoTest() {
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"com.mysql.jdbc.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:mysql://localhost:3306/contentsearch?useUnicode=true&characterEncoding=UTF-8");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"root");
	}
 
         @Override
         protected DatabaseOperation getSetUpOperation() throws Exception {
                   return DatabaseOperation.CLEAN_INSERT;
         }
 
         @Override
         protected DatabaseOperation getTearDownOperation() throws Exception {
                   return DatabaseOperation.NONE;
         }
 
         protected void setUp() throws Exception {
                   super.setUp();
         }
 
         public void testInsert() {
                   Member member = new Member();
                   member.setName("sky");
                   member.setId(4);
                   memberDao.insertMember(member);
                   Member member2 = memberDao.findMemberById(5);
                   assertEquals(member.getName(), "sky");
         }
 
         public void testListAllMember() {
                   List<Member> members = memberDao.listAllMember();
                   assertEquals(members.size(), 3);
         }
 
         public void testListMemberById() {
                   Member member2 = memberDao.findMemberById(1);
                   assertEquals("sky",member2.getName());
         }
 
    //设置
         @Override
         protected IDataSet getDataSet() throws Exception {
		String path = MemberHibernateDaoTest.class.getResource("").getPath();
		String filepath = path + "memberSet.xml";
		return new FlatXmlDataSetBuilder().build(new FileInputStream(filepath));
         }
}