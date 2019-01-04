package org.infinity.javabasics.concurrency;

/**
 * jstack可以观察到
 * java.lang.Thread.State: WAITING (on object monitor)
 */
public class CannotTerminateDemo {

    private Object lock1 = new Object();

    private void run() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    lock1.wait();//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
    }

    public static void main(String[] args) {
        CannotTerminateDemo demo = new CannotTerminateDemo();
        demo.run();
    }
}
