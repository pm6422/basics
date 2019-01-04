package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);

        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }, "t2");

        t1.start();
        t2.start();
        Thread.sleep(10_000);
        t1.interrupt();
        t2.interrupt();
    }
}

