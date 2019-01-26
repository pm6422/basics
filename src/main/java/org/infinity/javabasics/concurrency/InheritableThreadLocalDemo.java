package org.infinity.javabasics.concurrency;

public class InheritableThreadLocalDemo {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        InheritableThreadLocalDemo demo = new InheritableThreadLocalDemo();
        demo.run();
    }

    private void run() throws InterruptedException {
        inheritableThreadLocal.set("主线程设置的值");
        threadLocal.set("threadlocal");
        Thread thread = new Thread(() -> {
            System.out.println("子线程获取到主线程的值为：" + inheritableThreadLocal.get());
            System.out.println("获取不到主线程的值：" + threadLocal.get());
        });
        thread.start();
        thread.join();
    }
}
