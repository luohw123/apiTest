package com.javaapi.test.IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 时间处理isacceptable 未处理
 * 
 * @author hncw
 * 
 */
public class TestServer {
	public static Selector selector;
	static {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel;
		try {

			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress("localhost", 10002));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				selector.select();
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					it.remove();
					handle(sk);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void handle(SelectionKey selectionKey) {

		if (selectionKey.isAcceptable()) {
			try {
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey
						.channel();
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				socketChannel.register(selector, SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (selectionKey.isReadable()) {
			try {
				SocketChannel socketChannel = (SocketChannel) selectionKey
						.channel();
				ByteBuffer dst = ByteBuffer.allocate(1024);
				dst.clear();
				socketChannel.read(dst);
				System.out.println("服务端收到得信息==>"
						+ new String(dst.array(), "utf-8"));
				dst.flip();
				socketChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// serverSocketChannel.
		}
	}
}