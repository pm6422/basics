package org.infinity.javabasics.concurrency;

/**
 * 代码好像不好使
 */
public class VolatileNotAtomic {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileNotAtomic test = new VolatileNotAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10; j++)
                        test.increase();
                    System.out.println("Active thread count in real time: " + Thread.activeCount());
                }
            }.start();
        }
        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(test.inc);//这段代码每次运行结果都不一致，都是一个小于1000的数字
    }
}
