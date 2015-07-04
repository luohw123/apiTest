package com.javaapi.test.testUtil.IO.blockIo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class LoginServer {

	/**
	 * 阻塞IO
	 * 
	 * @param args
	 */
	@Test
	public void blockIO() {

		try {

			// 1.建立一个服务器Socket(ServerSocket)绑定指定端口

			ServerSocket serverSocket = new ServerSocket(10002);

			// 2.使用accept()方法阻止等待监听，获得新连接

			Socket socket = serverSocket.accept();

			// 3.获得输入流

			InputStream is = socket.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// 获得输出流

			OutputStream os = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(os);

			// 4.读取用户输入信息

			String info = null;

			while (!((info = br.readLine()) == null)) {

				System.out.println("我是服务器，用户信息为：" + info);

			}

			// 给客户一个响应

			String reply = "welcome";

			pw.write(reply);

			pw.flush();

			// 5.关闭资源

			pw.close();

			os.close();

			br.close();

			is.close();

			socket.close();

			serverSocket.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}