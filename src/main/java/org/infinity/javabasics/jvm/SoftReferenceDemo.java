package org.infinity.javabasics.jvm;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceDemo {

    public static void main(String[] args) {
        Object reference = new Object();
        System.out.println(reference);
        SoftReference<Object> softReference = new SoftReference<>(reference);
        reference = null;
        System.gc();
        System.out.println(softReference.get());//强制GC之后，SoftReference所引用的对象并没有被回收
    }

    /**
     * 正确使用引用对象demo
     */
    private static void correctUseRefObjDemo() {
        List<String> myList = new ArrayList<>();
        SoftReference<List<String>> refObj = new SoftReference<>(myList);
        List<String> list = refObj.get();// 正确的使用，使用强引用指向对象保证获得对象之后不会被回收
        if (null != list) {
            list.add("hello");
        } else {
            // 整个列表已经被垃圾回收了，做其他处理
        }
    }

    /**
     * 错误使用引用对象demo
     */
    private static void incorrectUseRefObjDemo() {
        List<String> myList = new ArrayList<>();
        SoftReference<List<String>> refObj = new SoftReference<>(myList);
        if (null != refObj.get()) {// 错误的使用，在检查对象非空到使用对象期间，对象可能已经被回收.可能出现空指针异常
            refObj.get().add("hello");
        }
    }
}
