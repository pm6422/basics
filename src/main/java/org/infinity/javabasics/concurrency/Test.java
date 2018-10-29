package org.infinity.javabasics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        new Test().startTenThreads();
    }

    private ExecutorService executor = Executors.newFixedThreadPool(50);

    public void startTenThreads() {
        for (int i = 0; i < 10; i++) {
            executor.execute(new FooWorker(i));
        }
    }

    private final class FooWorker implements Runnable {
        private int threadNum;

        public FooWorker(int threadNum) {
            this.threadNum = threadNum;
        }

        public void run() {
            System.out.println("Thread " + threadNum + " starting");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + threadNum + " finished");
        }
    }
}