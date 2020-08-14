package org.infinity.javabasics.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 在step1跟step2中，都只是调用ConcurrentHashMap的方法，各自都是原子操作，是线程安全的。
 * 但是他们组合在一起的时候就会有问题了，A线程在进入方法后，通过map.get("key")拿到key的值，
 * 刚把这个值读取出来还没有加1的时候，线程B也进来了，那么这导致线程A和线程B拿到的key是一样的。
 * 不仅仅是在ConcurrentHashMap，在其他的线程安全的容器比如Vector之类的也会出现如此情况，所以在使用这些容器的时候还是不能大意。
 */
public class ConcurrentHashMapThreadSafeIssueDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>();
        map1.put("key", 0);

        ExecutorService threadPool1 = Executors.newFixedThreadPool(8);
        IntStream.range(0, 1000).forEach(i -> {
            threadPool1.execute(() -> {
                int key = map1.get("key") + 1; //step 1: single atomic operation is thread safe
                map1.put("key", key); //step 2: single atomic operation is thread safe
                // but two steps composition is not thread safe
            });
        });
        threadPool1.shutdown();
        if (threadPool1.awaitTermination(1, TimeUnit.HOURS)) {
            // 预期结果为1000，但是多次执行可以发现每次结果都不一样，说明非线程安全
            System.out.println("------" + map1.get("key") + "------");
        }

        ConcurrentHashMap<String, Integer> map2 = new ConcurrentHashMap<>();
        map2.put("key", 0);

        ExecutorService threadPool2 = Executors.newFixedThreadPool(8);
        IntStream.range(0, 1000).forEach(i -> {
            threadPool2.execute(() -> {
                // make it thread safe
                synchronized (ConcurrentHashMapThreadSafeIssueDemo.class) {
                    int key = map2.get("key") + 1;
                    map2.put("key", key);
                }
            });
        });
        threadPool2.shutdown();
        if (threadPool2.awaitTermination(1, TimeUnit.HOURS)) {
            // 预期结果为1000，线程安全
            System.out.println("------" + map2.get("key") + "------");
        }
    }
}
