package org.infinity.javabasics.string;

public class InternDemo {
    public static void main(String[] args) {
        // Case 1
        String a = "abc";
        // a.intern()发现常量池中已经存在"abc"，则直接返回常量池中的对象引用
        System.out.println(a.intern() == a);//true

        // Case 2
        String b = new String("test");// 同时在堆上和常量池中生成"test"字符串
        // b.intern()发现常量池中已经存在"test"，则直接返回常量池中的对象引用，而b为堆上的对象引用，所以二者不相同
        System.out.println(b.intern() == b);//false

        String c = "test";
        // b.intern()发现常量池中已经存在"test"，则直接返回常量池中的对象引用，c同样为常量池中的对象引用，所以二者相同
        System.out.println(b.intern() == c);//true

        // Case 3
        // 实际上会转为StringBuffer的append()方法调用然后再执行toString()方法，只在堆上生成"john"字符串
        String f = new String("jo") + new String("hn");
        // f.intern()发现常量池中不存在"john"，则将当前对象引用复制到常量池，并且返回的是当前对象位于堆上的引用，f同样为当前对象位于堆上的引用，所以二者相同
        System.out.println(f.intern() == f);//true

        // 常量池中放入"john"
        String g = "john";
        // f.intern()发现常量池中已经存在"john"，则直接返回常量池中的对象引用，g同样为常量池中的对象引用，所以二者相同
        System.out.println(f.intern() == g);//true
//        System.out.println(f.intern() == f);//true 搞不懂为什么是true


        String a1 = "AA";
        System.out.println(a1.intern() == a1); //true
        String a2 = new String("B") + new String("B");//只在堆上创建对象

        a2.intern();
        String a3 = new String("B") + new String("B");//只在堆上创建对象
        System.out.println(a3.intern() == a2);//true
        System.out.println(a3.intern() == a3);//false
        String a4 = new String("C") + new String("C");
        System.out.println(a4.intern() == a4); //true
    }
}
