package com.javaapi.test.spring.zotherFeature.selfnamespacehandler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {
	public void init() {
		registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
	}
}