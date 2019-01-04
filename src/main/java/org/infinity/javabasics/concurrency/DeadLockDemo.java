package org.infinity.javabasics.concurrency;

/**
 * 使用jps+jstack pid组合可以观察到死锁
 */
public class DeadLockDemo {
    private Object lockA = new Object();
    private Object lockB = new Object();

    private void run() {
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

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        demo.run();
    }
}