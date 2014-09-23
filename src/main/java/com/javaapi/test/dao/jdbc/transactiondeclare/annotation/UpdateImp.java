package com.javaapi.test.dao.jdbc.transactiondeclare.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("targetUpdateService")
@Transactional
public class UpdateImp implements IupdateService {
	@Autowired
	JdbcTemplate jdbcTemplate;
    //        private static final String sql = "insert csc_sns_dev.tbl_b (id,val) values(4,'kk')";

    private static final String sql = "update matchs set league_name='斯伐乙西2'  where bet007_id=595959";
	
	@Override
	public void update() {
		jdbcTemplate.update(sql);
	}

}
