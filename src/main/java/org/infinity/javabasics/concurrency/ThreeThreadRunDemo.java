package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThreadRunDemo {

    private int  threadFlag = 1;
    private Lock lock       = new ReentrantLock(true);

    private void run() {
        class Task implements Runnable {
            private int count;
            private int conditionFlag;
            private int nextFlag;

            public Task(int count, int conditionFlag, int nextFlag) {
                this.count = count;
                this.conditionFlag = conditionFlag;
                this.nextFlag = nextFlag;
            }

            @Override
            public void run() {
                for (int i = 1; i <= count; ) {
                    if (threadFlag == conditionFlag) {
                        try {
                            lock.lock();
                            System.out.println(Thread.currentThread().getName());
                            i++;// 重点要把自增语句写在这里
                            threadFlag = nextFlag;
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }


        Thread t1 = new Thread(new Task(10, 1, 2), "Thread1");
        Thread t2 = new Thread(new Task(10, 2, 3), "Thread2");
        Thread t3 = new Thread(new Task(10, 3, 1), "Thread3");
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        new ThreeThreadRunDemo().run();
    }
}