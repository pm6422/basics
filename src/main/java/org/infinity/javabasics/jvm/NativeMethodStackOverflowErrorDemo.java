package org.infinity.javabasics.jvm;

/**
 * 程序会使整台电脑无任何响应，但看不到效果
 */
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