package org.infinity.javabasics.jvm;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        String key1 = new String("key1");
        String key2 = new String("key2");
        String key3 = new String("key3");
        weakHashMap.put(key1, "value1");
        weakHashMap.put(key2, "value2");
        weakHashMap.put(key3, "value3");
        System.out.println("before gc weakHashMap = " + weakHashMap + " , size=" + weakHashMap.size());
        // 使没有任何强引用指向key1
        key1 = null;
        // 通知JVM的gc进行垃圾回收
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after gc weakHashMap = " + weakHashMap + " , size=" + weakHashMap.size()); //输出：size=2
    }
}
