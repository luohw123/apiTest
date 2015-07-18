package com.javaapi.test.application.cache.redis;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class Client {
	@Autowired
	@Qualifier("redisTemplate")
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	@Qualifier("redisTemplateJdk")
	private RedisTemplate<Serializable,User>  redisTemplateJdk;  
	
	@Autowired
	@Qualifier("redisTemplateJackson")
	private RedisTemplate<Serializable,User>  redisTemplateJackson;  
	
	// string ser
	@Test
	public void testStringSet() {
		redisTemplate.opsForValue().set("nihao", "nihao2");
	}
	@Test
	public void testStringGet() {
		String string = redisTemplate.opsForValue().get("nihaojdk");
		System.err.println(string);
		 String userJackson = redisTemplate.opsForValue().get("nihaoJackson");
		 System.err.println(userJackson);
	}
	
	// jdk ser
	@Test
	public void testJdkSet() {
		User value = new User();
		value.setName("nihaojdk");
		value.setCreateTime(new Date());
		redisTemplateJdk.opsForValue().set("nihaojdk", value);
	}
	@Test
	public void testJdkGet() {
		 User user = redisTemplateJdk.opsForValue().get("nihaojdk");
		 System.err.println(user);
		 User userJackson = redisTemplateJdk.opsForValue().get("nihaoJackson");
		 System.err.println(userJackson);
	}
	// jackson ser
	@Test
	public void testJacksonSet() {
		User value = new User();
		value.setName("nihaoJackson");
		value.setCreateTime(new Date());
		redisTemplateJackson.opsForValue().set("nihaoJackson", value);
	}
	@Test
	public void testJacksonGet() {
		 User user = redisTemplateJackson.opsForValue().get("nihaoJackson");
		 System.err.println(user);
	}
}
