package org.infinity.javabasics.concurrency;

public class FinalFieldInstructionReorderDemo {

    private        int                              a; //普通域
    private final  int                              b; //final域
    private static FinalFieldInstructionReorderDemo finalDemo;

    public FinalFieldInstructionReorderDemo() {
        a = 1; // 1. 写普通域
        b = 2; // 2. 写final域
    }

    public static void writer() {
        finalDemo = new FinalFieldInstructionReorderDemo();
    }

    public static void reader() {
        FinalFieldInstructionReorderDemo demo = finalDemo; // 3.读对象引用
        int a = demo.a; // 4.读普通域
        System.out.println("a: " + a);
        int b = demo.b; // 5.读final域
        System.out.println("b: " + b);
    }

    public static void main(String[] args) {

        Thread write = new Thread(() -> {
            writer();
        }, "write");

        Thread read = new Thread(() -> {
            reader();
        }, "read");

        write.start();
        read.start();// read放在上一句则会报错。
    }
}
