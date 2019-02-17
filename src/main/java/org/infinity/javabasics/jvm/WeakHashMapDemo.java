package org.infinity.javabasics.jvm;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        Employee employee = new Employee("aaa");
        WeakHashMap map = new WeakHashMap();
        map.put(employee, "ss");
        System.out.println(map.size());
        System.gc();
        System.out.println(map.size());
        employee = null;
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(map.size()); // 输出0
    }
}
