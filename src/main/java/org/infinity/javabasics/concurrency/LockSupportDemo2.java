package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.LockSupport;

class TestThread extends Thread {
    private Object object;

    public TestThread(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println("before unpark");
        // 休眠,保证setBlocker(t, blocker)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取blocker
        System.out.println("Blocker info: " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.unpark((Thread) object);
        // 休眠500ms,保证先执行park中的setBlocker(t, null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Blocker info: " + LockSupport.getBlocker((Thread) object));
        System.out.println("after unpark");
    }
}


public class LockSupportDemo2 {
    public static void main(String[] args) throws Exception {
        TestThread thread = new TestThread(Thread.currentThread());
        thread.start();
        System.out.println("Before park");
        // 等待获取许可
        LockSupport.park("Park");
        System.out.println("After park");
    }
}
