package org.infinity.javabasics.jvm;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object reference = new Object();
        System.out.println(reference);
        WeakReference<Object> weakReference = new WeakReference<>(reference);
        reference = null;
        System.gc();
        System.out.println(weakReference.get());// null
    }
}
