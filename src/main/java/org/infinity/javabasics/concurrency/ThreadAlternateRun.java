package org.infinity.javabasics.concurrency;

public class ThreadAlternateRun {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Object lock = new Object();
        Thread a = new Thread(() -> {
            synchronized (lock) {
                System.out.println("A 1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("A 2");
                System.out.println("A 3");
            }
        });
        Thread b = new Thread(() -> {
            synchronized (lock) {
                System.out.println("B 1");
                System.out.println("B 2");
                System.out.println("B 3");
                lock.notify();
            }
        });
        a.start();
        b.start();
    }
}
