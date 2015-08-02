package com.javaapi.test.application.test.testmockito.demo1;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.javaapi.test.application.test.testmockito.demo1.po.RouteMatrix;

@RunWith(MockitoJUnitRunner.class)  
public class ClientMockito{  
	
   @InjectMocks  
   private IRouteService service = new RouteServiceImpl();
   
   @Mock  
   private IRouteMatrixDataProvider provider;

   private String	brand;

   private String	cInfo;  
  
  
   @Test  
   public void testGetAirlineCode() {
	   RouteMatrix value = new RouteMatrix();
	   value.setAirlineCode("kk_airCode");
	   when(provider.getRevenueRoute("HKG", "AMM", true)).thenReturn(value);
//	   verify(service).getAirlineCode("HKG", "AMM", this.brand, this.cInfo, true);
       String code = this.service.getAirlineCode("HKG", "AMM", this.brand, this.cInfo, true);  
       Assert.assertNotNull(code);  
       Assert.assertEquals("nihao", code);  
   }  
}  
