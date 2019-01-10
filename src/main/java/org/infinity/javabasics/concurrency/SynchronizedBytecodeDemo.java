package org.infinity.javabasics.concurrency;

public class SynchronizedBytecodeDemo {

    private int i;

    public static void main(String[] args) {
        synchronized (SynchronizedBytecodeDemo.class) {
        }
    }


    public synchronized void syncMethod() {
        i++;
    }
}
