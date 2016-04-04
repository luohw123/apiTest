package com.javaapi.test.application.test.testmockito.mockitSpring;

import com.javaapi.test.application.test.testmockito.mockitSpring.po.RouteMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

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

    /**
     * 方法调用时，如果不想指定一个明确的参数，就可以用下面这样的写法来表示任意的参数。
     * 但是这里也有一个限制就是，如果有一个参数使用了any***()，则所有的参数都必需使用这种方式，不能像下面这样写
     Mockito.when(this.provider.getRevenueRoute(Matchers.anyString(), Matchers.anyString(), true))

     */
   @Test  
   public void testGetAirlineCode2() {  
	   RouteMatrix value = new RouteMatrix();
	   value.setAirlineCode("kk_airCode");
	   when(provider.getRevenueRoute(Matchers.anyString(), Matchers.anyString(), Matchers.anyBoolean())).thenReturn(value);
       String code = this.service.getAirlineCode("HKG", "AMM", this.brand, this.cInfo, true);  
       Assert.assertNotNull(code);  
       Assert.assertEquals("nihao", code);  
   }  
}  
