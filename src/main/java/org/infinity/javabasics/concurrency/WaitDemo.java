package org.infinity.javabasics.concurrency;

import java.time.LocalDateTime;

public class WaitDemo {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    // 1 秒钟之后自动唤醒
                    lock.wait(1000);
                    System.out.println(LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
