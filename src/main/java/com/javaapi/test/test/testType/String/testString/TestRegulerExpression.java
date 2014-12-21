package com.javaapi.test.test.testType.String.testString;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import sun.misc.BASE64Decoder;

import com.aicaipiao.common.utils.MD5;
import com.aicaipiao.webclient.statistics.util.Base64Util;

public class TestRegulerExpression {

	@Test
	public void test() {
		String specialHint = "未开售:胜平负单关,胜平负过关,半全场胜平负单关,半全场胜平负过关";
		String[] forbiddenLotteryTypes = null;
		if (specialHint.trim().contains("未开")) {
			specialHint = specialHint.trim().replaceAll("未开.*:", "").trim();
			specialHint = specialHint.replaceAll("让球胜平负过关", "4071")
					.replaceAll("比分过关", "4073").replaceAll("总进球数过关", "4072")
					.replaceAll("半全场胜平负过关", "4074").replaceAll("胜平负过关", "4076");
			forbiddenLotteryTypes = specialHint.split(",");
		}
		if (forbiddenLotteryTypes == null) {
			return;
		}
		for (String string : forbiddenLotteryTypes) {
			System.out.println(string);
		}
		List<String> asList = Arrays.asList(forbiddenLotteryTypes);
		System.out.println("-----------");
		System.out.println(asList.contains("4074"));
	}
	@Test
	public void testS() {
        String thisMatchName ="  你  好  "; 
        System.out.println(thisMatchName.replaceAll("\\s*", ""));
        System.out.println("----------");
        System.out.println(StringUtils.deleteWhitespace(thisMatchName));
	}
	 public static void main(String[] args) throws IOException {
	        String account ="wangkun";
	        String pwd = "111111";
	        String passString = "sinaac"+MD5.encode(pwd);
//	        String desc ="描述";
//	        BASE64Decoder decoder = new BASE64Decoder();
//	        byte[] b = decoder.decodeBuffer(desc);
//	        desc = new String(b);
	        System.out.println("帐号:"+account);
	        System.out.println("原始密码:");
	        System.out.println("    "+pwd);
	        System.out.println("md5之后的密码:" );
	        System.out.println("    "+passString);
	        // 先发起
	        String urlcontent = "processCode=30001&account="+account+"&password="+passString+"&term=140912&amount=4&noteNumber=2&multiple=1&perAmount=2&founderPart=2&reservePart=1&publicType=2&subGame=4077&selectType=7";
	        System.out.println("urlcontent的原始内容:");
	        System.out.println("    "+urlcontent);
	        //按照1.先压缩-2.base64编码-3.urlencode       
	        String newStr = URLEncoder.encode(new String(Base64Util.encode(Base64Util.compressBytes(urlcontent.getBytes()))),
	                "UTF-8");
	        // 注意main方法需要urlencode2次，因为调用代码 (String) request.getParameter("urlcontent");  取出来得值默认被urldecode一次.
	        newStr = URLEncoder.encode(newStr);
	        System.out.println("urlcontent的加密后的内容:");
	        System.out.println("    "+newStr);
	        //64位解密
	        String data = Base64Util.decompressData(URLDecoder.decode(URLDecoder.decode(newStr), "UTF-8"));
	        System.out.println("urlcontent解密后的内容:");
	        System.out.println("    "+data);
	        System.out.println("提交的url例子");
	        System.out.println("    "+"http://www.aicai.com/jczqPrivateServlet?urlcontent="+newStr);
	        getUpload();
	        getUploadImmediate();
	    }

	    /**
	     * 
	     * @throws IOException 
	     * @create_time 2014年12月10日 下午12:12:15 
	     */
	    private static void getUploadImmediate() throws IOException {
	        System.out.println("--------------");
	        System.out.println("直接上传");
	        String account ="王昆1";
	        String pwd = "wangkun131108";
	        String planNo ="H141210086588257";
	        String passString = "sinaac"+MD5.encode(pwd);
	        String desc ="描述";
	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = decoder.decodeBuffer(desc);
	        desc = new String(b);
	        System.out.println("帐号:"+account);
	        System.out.println("原始密码:");
	        System.out.println("    "+pwd);
	        System.out.println("md5之后的密码:" );
	        System.out.println("    "+passString);
	        String urlcontent = "processCode=30000&account="+account+"&password="+passString+"&subGame=4077&amount=4&multiple=1&founderPart=1&reservePart=1&publicType=3&fileContent=SPF|141210005=3,141210006=3|2*1:1^SPF|141210005=3,141210006=3|2*1:1";
	        System.out.println("urlcontent的原始内容:");
	        System.out.println("    "+urlcontent);
	        String newStr = URLEncoder.encode(new String(Base64Util.encode(Base64Util.compressBytes(urlcontent.getBytes()))),
	                "UTF-8");
	        newStr = URLEncoder.encode(newStr);
	        System.out.println("urlcontent的加密后的内容:");
	        System.out.println("    "+newStr);
	        String data = Base64Util.decompressData(URLDecoder.decode(URLDecoder.decode(newStr), "UTF-8"));
	        System.out.println("urlcontent解密后的内容:");
	        System.out.println("    "+data);
	        System.out.println("提交的url例子");
	        System.out.println("    "+"http://www.aicai.com/jczqPrivateServlet?urlcontent="+newStr);
	    }

	    /**
	     * 
	     * @throws IOException 
	     * @create_time 2014年12月10日 下午12:12:15 
	     */
	    private static void getUpload() throws IOException {
	        System.out.println("--------------");
	        System.out.println("后上传");
	        String account ="wangkun";
	        String pwd = "111111";
	        String planNo ="H141210086588257";
	        String passString = "sinaac"+MD5.encode(pwd);
	        String desc ="描述";
	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = decoder.decodeBuffer(desc);
	        desc = new String(b);
	        System.out.println("帐号:"+account);
	        System.out.println("原始密码:");
	        System.out.println("    "+pwd);
	        System.out.println("md5之后的密码:" );
	        System.out.println("    "+passString);
	        String urlcontent = "processCode=30002&account="+account+"&password="+passString+"&planNo="+planNo+"&fileContent=SPF|141210005=3,141210006=3|2*1:1^SPF|141210005=3,141210006=3|2*1:1&playType=402&raceNo=5,6";
	        System.out.println("urlcontent的原始内容:");
	        System.out.println("    "+urlcontent);
	        String newStr = URLEncoder.encode(new String(Base64Util.encode(Base64Util.compressBytes(urlcontent.getBytes()))),
	                "UTF-8");
	        newStr = URLEncoder.encode(newStr);
	        System.out.println("urlcontent的加密后的内容:");
	        System.out.println("    "+newStr);
	        String data = Base64Util.decompressData(URLDecoder.decode(URLDecoder.decode(newStr), "UTF-8"));
	        System.out.println("urlcontent解密后的内容:");
	        System.out.println("    "+data);
	        System.out.println("提交的url例子");
	        System.out.println("    "+"http://www.aicai.com/jczqPrivateServlet?urlcontent="+newStr);
	    }
}
