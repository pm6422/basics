package org.infinity.javabasics.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger用于实现两个线程之间的数据交换，每个线程在完成一定的事务后想与对方交换数据，第一个先拿出数据的线程将一直等待第二个线程拿着数据来到时，才能彼此交换数据。
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Exchanger exchanger = new Exchanger();

        threadPool.execute(() -> {
            String data1 = "Elana";
            String data2 = "";
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "正准备把数据" + data1 + "换出去");
            try {
                data2 = (String) exchanger.exchange(data1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
        });

        threadPool.execute(() -> {
            String data2 = "Damon";
            String data1 = "";
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "正准备把数据" + data2 + "换出去");
            try {
                data1 = (String) exchanger.exchange(data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data1);
        });
        threadPool.shutdown();
    }
}
