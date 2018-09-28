package org.infinity.basics.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        int n = 2;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

        for (int i = 0; i < n; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

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
            System.out.println("startSignal.await()");
            startSignal.await();
            System.out.println("Do work");
            System.out.println("doneSignal.countDown()");
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }
}
