package org.infinity.javabasics.string;

public class StringJoinDemo {

    public static void main(String[] args) {
        // Case 1
        String a = "abc" + "edf";
        String b = "abcedf";
        System.out.println(a == b);// true

        // Case 2
        String c = new String("good ") + new String("morning");// 只在堆上创建对象
        String d = "good morning";
        System.out.println(c == d);// false
    }
}
