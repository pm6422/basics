package org.infinity.javabasics.concurrency;

public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                synchronized (lockB) { //尽量避免在持有一个锁的同时去申请另一个锁
                }
            }
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                synchronized (lockA) { //尽量避免在持有一个锁的同时去申请另一个锁
                }
            }
        }, "ThreadB");

        threadA.start();
        threadB.start();
    }
}