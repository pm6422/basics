package org.infinity.javabasics.concurrency;

import java.util.concurrent.TimeUnit;

public class SleepWaitNotifyDemo {
    public static void main(String[] args) {

        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1-thread A is waiting to get lock");
                synchronized (lock) {// lock area 1: 当进入线程A的lock同步块后，就无法进入线程2的lock area 2，直到线程A执行wait()方法后才可以进入线程2的lock area 2
                    try {
                        System.out.println("2-thread A get lock");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("4-thread A do wait method");
                        lock.wait();// 执行wait方法时，线程A会被挂起
                        System.out.println("7-wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3-thread B is waiting to get lock");
                synchronized (lock) {// lock area 2
                    try {
                        System.out.println("5-thread B get lock");
                        TimeUnit.SECONDS.sleep(5);
                        lock.notify();//执行notify方法时，会唤醒一个被挂起的线程A
                        System.out.println("6-thread B do notify method");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();
    }
}