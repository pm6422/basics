package org.infinity.javabasics.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo2 {

    private static List<String> list = new ArrayList<>();

    private static final ThreadLocal<List<String>> threadLocal = ThreadLocal.withInitial(() -> list);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get().size());// 结果为0
        list.add("sssd");
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get().size());// 结果为1

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get().size());// 结果为1
        }).start();
    }
}
