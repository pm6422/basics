package org.infinity.basics.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                countDownLatch.countDown();
                System.out.println("Execute " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                countDownLatch.countDown();
                System.out.println("Execute " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread2").start();


        countDownLatch.await();           // wait for all to finish
        System.out.println("Execute " + Thread.currentThread().getName());
    }
}


