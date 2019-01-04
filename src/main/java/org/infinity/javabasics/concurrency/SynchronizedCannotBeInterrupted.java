package org.infinity.javabasics.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizedCannotBeInterrupted {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

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
