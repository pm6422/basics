package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAlternateRunDemo3 {

    private boolean evenFlag = true; // 起始于偶数
    private Lock    lock     = new ReentrantLock();

    public static void main(String[] args) {
        new ThreadAlternateRunDemo3().run();
    }

    private void run() {
        class Task implements Runnable {
            private int     startIndex;
            private boolean opposite;

            Task(int startIndex, boolean opposite) {
                this.startIndex = startIndex;
                this.opposite = opposite;
            }

            @Override
            public void run() {
                for (int i = startIndex; i <= startIndex + 100; ) { // 第三个statement为空时，if条件为false时形成了死循环，直到另外线程获得到锁后修改条件为true。
                    // XOR是异或运算，操作数不同时为true，否则为false
                    if (evenFlag ^ this.opposite) {
                        try {
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + ":" + i);
                            i += 2;// 重点要把自增语句写在这里
                            evenFlag = this.opposite;
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }

        Thread t1 = new Thread(new Task(0, false), "偶");
        Thread t2 = new Thread(new Task(1, true), "奇");
        t1.start();
        t2.start();
    }
}