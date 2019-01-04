package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockInDifferentThreadsDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("thread1");
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            // java.lang.IllegalMonitorStateException
            lock.unlock();
            System.out.println("thread2");
        });

        thread2.start();
    }
}
