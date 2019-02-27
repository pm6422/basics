package org.infinity.javabasics.collection;

import com.google.common.collect.Lists;

import java.util.List;

public class ArrayListConcurrentModificationExceptionDemo {

    public static void main(String[] args) {
//      List<String> list = Arrays.asList("QQ", "AA", "WW", "QQ", "AA", "WW");// 这个版本调用removeIf会报UnsupportedOperationException
        List<String> list = Lists.newArrayList("QQ", "AA", "WW", "QQ", "AA", "WW");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add("" + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.removeIf(x -> x.equals("QQ"));
            }

            // the below statement is ok
//            list.stream().filter(x-> x.equals("QQ")).collect(Collectors.toList());
        });

        t1.start();
        t2.start();

    }
}
