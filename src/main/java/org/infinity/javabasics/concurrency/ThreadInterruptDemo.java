package org.infinity.javabasics.concurrency;

public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.currentThread().sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Thread.sleep(100L);
        thread.interrupt();
    }
}
