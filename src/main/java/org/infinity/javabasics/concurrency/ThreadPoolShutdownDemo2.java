package org.infinity.javabasics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Refer
 * https://www.toutiao.com/i6648464915063374350/
 */
public class ThreadPoolShutdownDemo2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> System.out.println("Hello "));
        System.out.println("World");

// 不shutdown是不会退出应用程序的
//        service.shutdown();
    }
}
