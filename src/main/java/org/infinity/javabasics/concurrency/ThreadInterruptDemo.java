package org.infinity.javabasics.concurrency;

public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.currentThread().sleep(10000L);
            } catch (InterruptedException e) {
                System.out.println("exception thrown");
            }

            System.out.println("state: " + Thread.currentThread().getState().name());
            System.out.println("continue");
            System.out.println("interrupted: " + Thread.currentThread().isInterrupted());
        });

        thread.start();
        //睡眠一秒钟，保证线程thread阻塞在sleep方法
        Thread.sleep(100L);
        thread.interrupt();
    }
}
