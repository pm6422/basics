package org.infinity.javabasics.concurrency;

public class ThreadAlternateRunDemo1 {

    private Object lock = new Object();

    public static void main(String[] args) {
        new ThreadAlternateRunDemo1().run();
    }

    private void run() {
        Thread threadEven = new Thread(() -> {
            for (int i = 0; i <= 100; i = i + 2) {
                synchronized (lock) {// 通过synchronized抢先获得到锁的线程进入RUNNABLE状态，未获得锁的处于BLOCKED状态
                    System.out.println(Thread.currentThread().getName() + "->" + i);
                    System.out.println(Thread.currentThread().getName() + " notify");
                    lock.notify();// 调用notify后另外一个线程会处于BLOCKED状态，DEBUG模式后使用JVISUALVM可以看到，当本线程未释放对象锁的时候另外一个线程即使处于BLOCKED状态也不会运行
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        lock.wait();// 调用该方法的线程进入WAITING状态并释放对象锁，然后线程暂停于此直到唤醒，另外处于WAITING状态的线程无法直接进入RUNNABLE状态，必须先进入BLOCKED状态，然后获取到monitor后才可以进入RUNNABLE状态
                        System.out.println(Thread.currentThread().getName() + " obtained monitor");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "threadEven");

        Thread threadOdd = new Thread(() -> {
            for (int i = 1; i <= 101; i = i + 2) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "->" + i);
                    System.out.println(Thread.currentThread().getName() + " notify");
                    lock.notify();
                    try {
                        if (i < 101) {
                            System.out.println(Thread.currentThread().getName() + " wait");
                            lock.wait();
                            System.out.println(Thread.currentThread().getName() + " obtained monitor");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "ThreadOdd");


        threadEven.start();
        threadOdd.start();
    }
}
