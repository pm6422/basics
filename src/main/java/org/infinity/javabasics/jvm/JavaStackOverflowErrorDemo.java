package org.infinity.javabasics.jvm;

public class JavaStackOverflowErrorDemo {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        JavaStackOverflowErrorDemo mock = new JavaStackOverflowErrorDemo();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}