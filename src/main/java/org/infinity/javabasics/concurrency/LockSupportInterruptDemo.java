package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.LockSupport;

public class LockSupportInterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                LockSupport.park();
                System.out.println("continue");
            } catch (Exception e) {
                e.getStackTrace();
            }
        });

        t1.start();
        Thread.sleep(1000);
        t1.interrupt(); // interrupt可以终止t1的暂停，可以继续执行
    }
}
