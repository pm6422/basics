package org.infinity.javabasics.concurrency;

class ClassToTest {
    private int count;

    public synchronized void incr() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

public class ThreadSafeTester extends Thread {
    private ClassToTest classToTest;

    public ThreadSafeTester(ClassToTest classToTest) {
        this.classToTest = classToTest;
    }

    @Override
    public void run() {
        try {
            // 模拟不同长短的处理时间
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
        }
        classToTest.incr();
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 100;
        ClassToTest classToTest = new ClassToTest();
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new ThreadSafeTester(classToTest);
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }

        System.out.println(classToTest.getCount());
    }
}