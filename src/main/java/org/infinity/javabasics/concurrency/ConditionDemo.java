package org.infinity.javabasics.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private Lock      lock      = new ReentrantLock();
    private Condition condition = lock.newCondition();// 通过锁调用newCondition()来创建出Condition

    public static void main(String[] args) throws Exception {
        ConditionDemo demo = new ConditionDemo();
        MyThread thread = new MyThread(demo);
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        demo.signal(); // condition.signal()等效于object.notify()
    }

    public void await() {
        try {
            lock.lock();
            System.out.println("await时间为：" + System.currentTimeMillis());
            condition.await(); // condition.await()等效于object.wait()，Condition的await()方法会释放锁。调用后暂停执行，直到condition.signal()
            System.out.println("await等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal时间为：" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class MyThread extends Thread {
    private ConditionDemo demo;

    public MyThread(ConditionDemo demo) {
        this.demo = demo;
    }

    public void run() {
        demo.await();
    }
}
