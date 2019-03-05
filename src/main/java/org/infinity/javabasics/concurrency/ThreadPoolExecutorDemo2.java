package org.infinity.javabasics.concurrency;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo2 {

    public static void main(String[] args) {
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(10);
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        ExecutorService threadPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                workingQueue, rejectedExecutionHandler);

        for (int i = 0; i < 100; i++) {
            final int threadNo = i;
            threadPool.submit((Callable<Boolean>) () -> {
                System.out.println("thread " + String.valueOf(threadNo) + " is called");
                Thread.sleep(10000);
                System.out.println("thread " + String.valueOf(threadNo) + " is awake");
                throw new Exception();
            });
        }

        threadPool.shutdown();
    }
}
