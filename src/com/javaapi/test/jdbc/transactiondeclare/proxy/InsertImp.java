package com.javaapi.test.jdbc.transactiondeclare.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service("targetInsertService")
public class InsertImp implements IinsertService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("proxyUpdateService")
	IupdateService iupdateService;
	
	@Override
	public void insert() {
		String sql = "insert csc_sns_dev.tbl_b (id,val) values(3,'kk')";
		jdbcTemplate.update(sql);
		// 这是个服务
		iupdateService.update();
//		throw new RuntimeException("方法抛出异常,导致异常回滚");
	}

}
