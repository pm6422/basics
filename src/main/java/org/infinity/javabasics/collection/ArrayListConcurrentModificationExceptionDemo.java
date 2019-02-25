package org.infinity.javabasics.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListConcurrentModificationExceptionDemo {

    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        List<String> list = Arrays.asList("QQ", "AA", "WW", "QQ", "AA", "WW");

        all.addAll(list);
        for (String str : all) {
            if (str.equals("QQ")) {
                all.remove(str);
            }
        }
    }
}
