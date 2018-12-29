package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockInDifferentThreadsDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            lock.lock();
        });

        thread1.start();


        Thread thread2 = new Thread(() -> {
            // java.lang.IllegalMonitorStateException
            lock.unlock();
        });

        thread2.start();
    }
}
