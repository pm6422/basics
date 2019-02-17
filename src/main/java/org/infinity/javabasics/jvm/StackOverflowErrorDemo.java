package org.infinity.javabasics.jvm;

public class StackOverflowErrorDemo {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackOverflowErrorDemo mock = new StackOverflowErrorDemo();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}