package org.infinity.javabasics.basic;

public class StringAssignment {

    public static void main(String[] args) {
        String s = "123";
        changeString(s);
        System.out.println(s);
    }

    private static void changeString(String s) {
        s = "abc";
    }
}
