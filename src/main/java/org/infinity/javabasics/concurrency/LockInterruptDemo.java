package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lock(); // main线程已经占用锁
        Thread.sleep(1000);

        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly(); // main线程占用锁后无法继续执行直到t1发生中断抛出InterruptedException才可以继续执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();// main线程占用锁后无法继续执行
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }, "t2");

        t1.start();
        t2.start();
        // 执行完上面两个语句后，AQS队列里面状态为t1->t2，并且t1和t2通过LockSupport.park(this)暂停执行处于WAITING状态，可以看AQS源码
        // t1和t2此时可以响应中断，后面的逻辑执行interrupt()可以终止暂停继续执行，
        // 对于lockInterruptibly()来说继续执行会直接抛出InterruptedException，
        // 但是对于lock()来说继续执行会再一次暂停并处于WAITING状态
        Thread.sleep(10_000);
        t1.interrupt();
        t2.interrupt();
    }
}

