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
        //睡眠一秒钟，保证线程a已经计算完成，阻塞在LockSupport.park()
        Thread.sleep(1000);
        LockSupport.unpark(a);// 终止暂停，继续执行System.out.println(sum);
    }
}
