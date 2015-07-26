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

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("applicationContext.xml")  
public class RouteServiceTestCase{  
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
