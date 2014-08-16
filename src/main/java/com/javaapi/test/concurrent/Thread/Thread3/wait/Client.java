package com.javaapi.test.concurrent.Thread.Thread3.wait;

/**
 * http://zhangjunhd.blog.51cto.com/113473/71387
 * http://uule.iteye.com/blog/1100799
 *1  调用sleep()和yield()的时候锁并没有被释放，而调用wait()将释放锁。这样另一个任务（线程）可以获得当前对象的锁，从而进入它的synchronized方法中。可以通过notify()/notifyAll()，或者时间到期，从wait()中恢复执行。
只能在同步控制方法或同步块中调用wait()、notify()和notifyAll()。如果在非同步的方法里调用这些方法，在运行时会抛出IllegalMonitorStateException异常。
 *2 wait和sleep 对比下,wait休眠后会释放锁,而sleep休眠后不会释放锁
 */
public class Client {
	public static void main(String[] args) {
		Game game = new Game();
		for (int i = 0; i < 10; i++)
			game.addPlayer(new Athlete(i, game));
		new Thread(game).start();
	}
}
