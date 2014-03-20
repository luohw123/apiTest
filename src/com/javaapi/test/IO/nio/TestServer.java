package com.javaapi.test.IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

public class TestServer {
	@Test
	public void nioServer() {
		try {
			ByteBuffer bb = ByteBuffer.allocate(1024);
			Selector selector = Selector.open();
			SocketAddress address = new InetSocketAddress(10002);
			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.socket().bind(address);
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_ACCEPT);

			selector.select();
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			int count;
			while (it.hasNext()) {
				SelectionKey selectionKey = it.next();
				ServerSocketChannel scChannel = (ServerSocketChannel) selectionKey
						.channel();
				scChannel.configureBlocking(false);
				SocketChannel schannel = scChannel.accept();
				bb.clear();
				count = schannel.read(bb);
				bb.flip();
				System.out.println(new String(bb.array(), 0, count));
				schannel.register(selector, SelectionKey.OP_READ);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
