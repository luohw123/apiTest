package com.javaapi.test.spring.zotherFeature.rpc.hessian;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.aicaipiao.common.entity.enumerated.Game;
import com.aicaipiao.common.entity.issue.BetGameIssue;
import com.aicaipiao.common.service.issue.IssueManagerService;

/**
 * 注意在linux系统下使用FileSystemXmlApplication 要加 "/"
 * 
 */
public class ClientPlain {
	@Test
	public void test() {
		String path = ClientPlain.class.getResource("").getPath();
		String filename = path + "remoting-core-service-client.xml";
		System.out.println(filename);
		ApplicationContext app = new FileSystemXmlApplicationContext("/" + filename);
		IssueManagerService issueService = (IssueManagerService) app.getBean("issueManagerService");
		BetGameIssue issue = issueService.getPrevIssue(Game.JJ_DCSF);
		System.out.println(issue.getIssueNo());
	}
}
