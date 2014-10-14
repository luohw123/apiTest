package com.javaapi.test.spring.aop.aspectj.xml1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * http://pingping0524.iteye.com/blog/262482
 *
 */
/**1 父类依赖得静态代码块,父类依赖得实例代码块</br>2 父类静态代码块,父类实例代码块</br>
 * 3 调用类得静态代码块,调用类的实例代码块 </br>  4 直接依赖的静态代码块,直接依赖的实例代码块</br>
 * @author wk
 *
 */
public class ClientPlain {
	@Test
	public void test() {
		String path=ClientPlain.class.getResource("").getPath();
		String filename = path+"applicationContext.xml";
		ApplicationContext app=new FileSystemXmlApplicationContext(filename);
		MyBService myservice = (MyBService) app.getBean("mybservice");
		myservice.foo();
	}
}
