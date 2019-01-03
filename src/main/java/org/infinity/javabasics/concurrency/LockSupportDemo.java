package org.infinity.javabasics.concurrency;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println(sum);
        });
        a.start();
        // 不像object.wait()需要sleep，LockSupport就支持主线程先调用unpark后，线程A再调用park而不被阻塞
        // Thread.sleep(1000);
        LockSupport.unpark(a);// 终止暂停，继续执行System.out.println(sum);
    }
}
