package org.infinity.javabasics.jvm;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void main(String[] args) {
        Object reference = new Object();
        System.out.println(reference);
        SoftReference<Object> softReference = new SoftReference<>(reference);
        reference = null;
        System.gc();
        System.out.println(softReference.get());//强制GC之后，SoftReference所引用的对象并没有被回收
    }
}
