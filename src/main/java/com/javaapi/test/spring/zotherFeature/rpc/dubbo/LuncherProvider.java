package com.javaapi.test.spring.zotherFeature.rpc.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class LuncherProvider  {
	public static void main(String[] args) throws InterruptedException{
		LuncherProvider luncher=new LuncherProvider();
		luncher.start();
		Thread.sleep(1000*60*10);
	}
	
	void start(){
        String configLocation = LuncherProvider.class.getResource("").getPath() + "dubbo-provider.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext("file:" + configLocation);
		String [] names=context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names){
            System.out.println(string);
            System.out.println(",");
        }
        System.out.println("=============end===========");
	}
}
