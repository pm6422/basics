package org.infinity.javabasics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolShutdownDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.execute(() -> {
            System.out.println("Thread 1 starting");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 finished");
        });

        threadPool.execute(() -> {
            System.out.println("Thread 2 starting");
            for (int i = 0; i <= 10000; i++) {
                for (int j = 0; j <= 100000; j++) {
                    if (j % 100000 == 0)
                        System.out.println(i * j);
                }
            }
            System.out.println("Thread 2 finished");
        });

        threadPool.shutdownNow();

        threadPool.execute(() -> {
            System.out.println("Thread 3 starting");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 3 finished");
        });
    }

}
