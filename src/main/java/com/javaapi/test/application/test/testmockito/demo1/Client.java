package com.javaapi.test.application.test.testmockito.demo1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * http://blog.csdn.net/fireofjava/article/details/8687128</br>
 * http://www.cnblogs.com/alphablox/archive/2013/04/06/3002152.html</br>
 * 另外一种注入mock方式,(可能是以前的实现方式,现在的实现方式是@InjectMocks,或者xml配置)http://lvyanglin.iteye.com/blog/1025956</br>
 * 测试controller,除了@InjectMocks ,另外可以利用配置xml ,实现mock注入
 * http://www.tuicool.com/articles/vmEb6jq</br>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("applicationContext.xml")  
public class Client{  
   @InjectMocks  
   @Autowired  
   private IRouteService service;  
  
   @Mock  
   private IRouteMatrixDataProvider provider;

   private String	brand;

   private String	cInfo;  
  
   @Before  
   public void myBefore() {  
       MockitoAnnotations.initMocks(this);  
   }  
  
   @Test  
   public void testGetAirlineCode() {  
       RouteMatrix rm = new RouteMatrix();  
       rm.setAirlineCode("kkk");  
       Mockito.when(this.provider.getRevenueRoute("HKG", "AMM", true)).thenReturn(rm);  
       String code = this.service.getAirlineCode("HKG", "AMM", this.brand, this.cInfo, true);  
       Assert.assertNotNull(code);  
       Assert.assertEquals("nihao", code);  
       code = this.service.getAirlineCode("HKG", "KKK", this.brand, this.cInfo, true);  
       Assert.assertNull(code);  
   }  
  
   @Test  
   public void testGetAirlineCode2() {  
       String code = this.service.getAirlineCode("HKG", "AMM", this.brand, this.cInfo, true);  
       Assert.assertNotNull(code);  
       Assert.assertEquals("kkk", code);  
   }  
}  
