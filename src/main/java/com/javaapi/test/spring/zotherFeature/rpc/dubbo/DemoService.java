package com.javaapi.test.spring.zotherFeature.rpc.dubbo;
public interface DemoService {
	
    public void sayHello();
    
    public String returnHello();
    
    public MsgInfo returnMsgInfo(MsgInfo info);
    
}