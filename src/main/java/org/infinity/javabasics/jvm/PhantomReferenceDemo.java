package org.infinity.javabasics.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

    public static void main(String[] args) {
        Object reference = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        System.out.println(reference);
        PhantomReference<Object> weakReference = new PhantomReference<>(reference, referenceQueue);
        System.out.println(weakReference.get());// 无法通过虚引用取得一个对象实例，因此输出null
    }
}
