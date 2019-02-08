package org.infinity.javabasics.concurrency;

import org.infinity.javabasics.utils.Utils;

/**
 * Refer: https://www.toutiao.com/i6635503578167378436/
 * 使用jstack -l PID > thread_dump.txt可以观察到死锁
 * 使用kill -3 PID也可以在控制台看到死锁信息
 * 也可以使用Online Java Thread Dump Analyzer(TDA)查看死锁信息
 * http://spotify.github.io/threaddump-analyzer/
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
        System.out.println("PID:" + Utils.getPid());
    }
}