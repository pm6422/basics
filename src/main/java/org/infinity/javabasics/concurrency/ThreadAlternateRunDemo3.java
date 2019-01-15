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
            private boolean target;

            Task(int startIndex, boolean target) {
                this.startIndex = startIndex;
                this.target = target;
            }

            @Override
            public void run() {
                for (int i = startIndex; i <= startIndex + 100; ) { // 第三个statement为空时，evenFlag又为false时形成了死循环，直到另外线程获得到锁后修改evenFlag为true。
                    // XOR是异或运算，和target不一样的才为true，否则为false
                    if (evenFlag ^ this.target) {// evenFlag=false时会形成死循环，直到evenFlag=true
                        try {
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + ":" + i);
                            i += 2;// 重点要把自增语句写在这里
                            evenFlag = this.target;
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