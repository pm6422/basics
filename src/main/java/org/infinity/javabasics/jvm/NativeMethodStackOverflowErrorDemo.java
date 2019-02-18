package org.infinity.javabasics.jvm;

public class NativeMethodStackOverflowErrorDemo {
    private void runAlways() {
        while (true) {

        }
    }

    public void createThread() {
        while (true) {
            Thread thread = new Thread(() -> runAlways());
            thread.start();
        }
    }

    public static void main(String[] args) {
        NativeMethodStackOverflowErrorDemo test = new NativeMethodStackOverflowErrorDemo();
        test.createThread();
    }
}