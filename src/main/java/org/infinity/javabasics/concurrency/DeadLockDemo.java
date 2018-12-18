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
                synchronized (lockB) {
                }
            }
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                synchronized (lockA) {
                }
            }
        }, "ThreadB");

        threadA.start();
        threadB.start();
    }
}