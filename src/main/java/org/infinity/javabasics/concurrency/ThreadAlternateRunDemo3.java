package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAlternateRunDemo3 {

    private boolean evenFlag = true;
    private Lock    lock     = new ReentrantLock(true);

    public static void main(String[] args) {
        new ThreadAlternateRunDemo3().run();
    }


    private void run() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 100; ) { // 第三个statement为空时，evenFlag又为false时形成了死循环，直到另外线程获得到锁后修改evenFlag为true。
                if (evenFlag) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        i = i + 2;// 重点要把自增语句写在这里
                        evenFlag = false;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "EvenThread");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 101; ) {
                if (!evenFlag) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        i = i + 2;// 重点要把自增语句写在这里
                        evenFlag = true;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "OddThread");

        t1.start();
        t2.start();
    }
}