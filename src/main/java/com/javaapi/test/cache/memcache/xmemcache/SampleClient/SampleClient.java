package com.javaapi.test.cache.memcache.xmemcache.SampleClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 对于客户端往Socket的输出流里面写数据传递给服务端要注意一点，如果写操作之后程序不是对应着输出流的关闭，而是进行其他阻塞式的操作（
 * 比如从输入流里面读数据），记住要flush一下，只有这样服务端才能收到客户端发送的数据，否则可能会引起两边无限的互相等待。
 * 在稍后讲到客户端和服务端同时读和写的时候会说到这个问题。
 * 
 */
public class SampleClient {

	private int		port	= 11211;
	private String	host	= "127.0.0.1";

	@Test
	public void SampleClient() {
		try {
			Socket socket = new Socket(host, port);
			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			bufferedWriter.write("set hello 0 0 5 \r\n");
			bufferedWriter.write("12345\r\n");
			// bufferedWriter.write("\r\n");
			bufferedWriter.flush();
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			StringBuilder sb=new StringBuilder();
			String tempString;
			tempString = bufferedReader.readLine();
			if ("ERROR".equals(tempString)) {
				System.out.println("false");
			}
			if ("STORED".equals(tempString)) {
				System.out.println("true");
			}
			// while((tempString = bufferedReader.readLine()) != null){
			// sb.append(tempString);
			// System.out.println("==>" + tempString);
			// }
			System.out.println(sb.toString());
			bufferedWriter.close();
			outputStreamWriter.close();
			outputStream.close();
			inputStream.close();
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
