package org.infinity.javabasics.concurrency;

public class ThreadRuntimeExceptionDemo {
    private static Object  lock = new Object();
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("Starting " + Thread.currentThread().getName());
            if (flag) {
                throw new RuntimeException();//异常没有被捕获的话，这个线程就停止执行了
            }
            System.out.println("ended " + Thread.currentThread().getName());
        });
        t.start();
        t.join();
        System.out.println("ended main thread");
    }
}
