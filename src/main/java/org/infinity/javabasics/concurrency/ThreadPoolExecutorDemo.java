package org.infinity.javabasics.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable myRunnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.DiscardOldestPolicy());

        int size = 3;
        for (int i = 0; i < size; i++) {
            threadPool.execute(myRunnable);
        }
        System.out.println("---启动" + size + "个任务---");
        System.out.println("线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        size = 3;
        for (int i = 0; i < size; i++) {
            threadPool.execute(myRunnable);
        }
        System.out.println("---启动" + size + "个任务---");
        System.out.println("线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        size = 2;
        for (int i = 0; i < size; i++) {
            threadPool.execute(myRunnable);
        }
        System.out.println("---启动" + size + "个任务---");
        System.out.println("线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        size = 5;
        for (int i = 0; i < size; i++) {
            threadPool.execute(myRunnable);
        }
        System.out.println("---启动" + size + "个任务---");
        System.out.println("线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        TimeUnit.SECONDS.sleep(8);
        System.out.println("----8秒之后----");
        System.out.println("线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        threadPool.shutdown();
    }
}
