package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadGetLock {

    private static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
            } finally {

            }
        }, "thread1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
            } finally {

            }
        }, "thread2");

        Thread t3 = new Thread(() -> {
            try {
                lock.lock();
            } finally {

            }
        }, "thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
