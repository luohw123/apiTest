package com.javaapi.test.jdbc.transactiondeclare.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("targetUpdateService")
public class UpdateImp implements IupdateService {
	@Autowired
	JdbcTemplate jdbcTemplate;
//	private static String sql = "insert csc_sns_dev.tbl_b (id,val) values(4,'kk')";
	private static final String sql = "update matchs set league_name='斯伐乙西2'  where bet007_id=595959";
	
	@Override
	public void update() {
		jdbcTemplate.update(sql);
	}

}
