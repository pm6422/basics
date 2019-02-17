package org.infinity.javabasics.jvm;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        String key1 = new String("key1");//new String("key1")会在堆区创建一个可以被正常回收的对象
        String key2 = new String("key2");
        String key3 = new String("key3");
        String key4 = "key4";//String直接赋值，会在Java StringPool(字符串常量池)里创建一个String对象，通常不会被gc回收。思考一下原因？
        weakHashMap.put(key1, "value1");
        weakHashMap.put(key2, "value2");
        weakHashMap.put(key3, "value3");
        weakHashMap.put(key4, "value4");
        System.out.println("before gc weakHashMap = " + weakHashMap + " , size=" + weakHashMap.size());
        // 使没有任何强引用指向key1
        key1 = null;
        key4 = null;
        // 通知JVM的gc进行垃圾回收
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after gc weakHashMap = " + weakHashMap + " , size=" + weakHashMap.size()); //输出：size=3
    }
}
