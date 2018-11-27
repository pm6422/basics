package org.infinity.javabasics.concurrency;

public class ThreadSequentiallyRun {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Thread a = new Thread(() -> {
            printNumber("A");
        });
        Thread b = new Thread(() -> {
            System.out.println("B 开始等待 A");
            try {
                // 让父线程等待子线程结束之后才能继续运行，b为父线程，a为子线程
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printNumber("B");
        });
        b.start();
        a.start();
    }

    private static void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print:" + i);
        }
    }
}
