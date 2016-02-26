package com.javaapi.test.testUtil.fileUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//http://www.infoq.com/articles/Java-Thread-Pool-Performance-Tuning
//http://www.infoq.com/cn/articles/Java-Thread-Pool-Performance-Tuning/
//http://iamzhongyong.iteye.com/blog/1924745
//http://www.ibm.com/developerworks/library/j-jtp0730/index.html
//http://blog.csdn.net/feng27156/article/details/19333575
//        http://www.tuicool.com/articles/YfA3Ub
public class Test extends ThreadPoolExecutor {
    public Test(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

//    // Keep track of all of the request times
//    private final ConcurrentHashMap<Runnable, Long> timeOfRequest =
//            new ConcurrentHashMap<>();
//    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
//    private long lastArrivalTime;
//    // other variables are AtomicLongs and AtomicIntegers
//
//    @Override
//    protected void beforeExecute(Thread worker, Runnable task) {
//        super.beforeExecute(worker, task);
//        startTime.set(System.nanoTime());
//    }
//
//    @Override
//    protected void afterExecute(Runnable task, Throwable t) {
//        try {
//            totalServiceTime.addAndGet(System.nanoTime() - startTime.get());
//            totalPoolTime.addAndGet(startTime.get() - timeOfRequest.remove(task));
//            numberOfRequestsRetired.incrementAndGet();
//        } finally {
//            super.afterExecute(task, t);
//        }
//    }
//
//    @Override
//    public void execute(Runnable task) {
//        long now = System.nanoTime();
//
//        numberOfRequests.incrementAndGet();
//        synchronized (this) {
//            if (lastArrivalTime != 0L) {
//                aggregateInterRequestArrivalTime.addAndGet(now - lastArrivalTime);
//            }
//            lastArrivalTime = now;
//            timeOfRequest.put(task, now);
//        }
//        super.execute(task);
//    }
}