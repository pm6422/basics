package org.infinity.javabasics.jvm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object reference = new Object();
        System.out.println(reference);
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(reference, referenceQueue);
        reference = null;

        // 用于检查引用队列中的引用值被回收
        Thread checkRefQueueThread = new Thread(() -> {
            while (true) {
                Reference<? extends String> clearRef = referenceQueue.poll();
                if (null != clearRef) {
                    System.out.println("引用对象被回收, ref = " + clearRef + ", value = " + clearRef.get());
                }
            }
        });
        checkRefQueueThread.start();

        System.gc();
        System.out.println(weakReference.get());// null
    }
}
