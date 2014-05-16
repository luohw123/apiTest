package com.javaapi.test.jdbc.transactiondeclare.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("targetUpdateService")
public class UpdateImp implements IupdateService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void update() {
		String sql = "insert csc_sns_dev.tbl_b (id,val) values(4,'kk')";
		jdbcTemplate.update(sql);
	}

}
