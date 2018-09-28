package org.infinity.basics.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchSample1 {

    public static void main(String[] args) throws InterruptedException {
        int n = 2;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

        IntStream.range(0, n).forEach((i) -> {
            new Thread(new Worker(startSignal, doneSignal), "Thread" + i).start();// create and start threads
        });

        System.out.println("Do something else1");
        System.out.println("startSignal.countDown()");
        startSignal.countDown();      // let all threads proceed
        System.out.println("Do something else2");
        System.out.println("doneSignal.await()");
        doneSignal.await();           // wait for all to finish
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            System.out.println("startSignal.await() " + Thread.currentThread().getName());
            startSignal.await();
            System.out.println("Do work " + Thread.currentThread().getName());
            System.out.println("doneSignal.countDown() " + Thread.currentThread().getName());
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }
}
