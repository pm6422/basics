package org.infinity.javabasics.concurrency;

public class TerminateThreadDemo1 implements Runnable {
    // 用于停止线程，volatile保证可见性
    private volatile boolean stopRequested;

    @Override
    public void run() {
        stopRequested = false;
        int count = 0;
        while (!stopRequested) {
            System.out.println("Running ... count=" + count);
            count++;
            try {
                Thread.sleep(300);
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();// re-assert interrupt
            }
        }
        System.out.println("Stopped");
    }

    public void requestStop() {
        stopRequested = true;
    }

    public static void main(String[] args) {
        TerminateThreadDemo1 demo = new TerminateThreadDemo1();
        Thread t = new Thread(demo);
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException x) {
            // Leave blank intentionally
        }
        demo.requestStop();
    }
}