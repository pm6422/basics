package org.infinity.javabasics.basic;

public class StringAssignment {

    public static void main(String[] args) {
        String s = "123";
        changeString(s);
        System.out.println(s);
    }

    private static void changeString(String s) {
        // String是标准的不可变类(immutable)，对它的任何修改就是创建了一个新对象，再把引用指向该对象。
        s = "abc";
    }
}
