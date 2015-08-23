package com.javaapi.test.application.cache.redis.config;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingJackson extends ObjectMapper {
	private static final long serialVersionUID = -4609954020844770555L;
	private boolean useDefault = true;
	
	public MappingJackson (boolean useDefault) {
		this.useDefault = useDefault;
	}
	
//public MappingJackson() {
//	}
//	public void init() {
//		System.err.println("init");
//	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		if(!useDefault) {
			JacksonConfig.globalConfig(this);
			this.enableDefaultTyping(DefaultTyping.NON_FINAL,As.WRAPPER_OBJECT);
		}
		
	}
}
