package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockExceptionDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t = new Thread(() -> {
            lock.unlock(); // 释放锁前没有获取锁则抛java.lang.IllegalMonitorStateException
            System.out.println("thread");
        });

        t.start();
    }
}
