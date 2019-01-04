package org.infinity.javabasics.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizedCannotBeInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t = new Thread(() -> {
            synchronized (lock) {
                for (; ; ) {
                    System.out.println(1);
                }
            }
        }, "Thread1");

        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
