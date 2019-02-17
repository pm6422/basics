package org.infinity.javabasics.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK7版本的类的运行时常量池移到了Java堆中，因此会出现OutOfMemoryError: Java heap space
 * JDK6版本的类的运行时常量池在永久代，因此会出现OutOfMemoryError: PermGen Space
 * <p>
 * VM Options: -Xmx8m
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.Arrays.copyOf(Arrays.java:3332)
 * at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
 * at java.lang.StringBuilder.append(StringBuilder.java:136)
 * at org.infinity.javabasics.jvm.StringConstantsOutOfMemoryError.main(StringConstantsOutOfMemoryError.java:13)
 */
public class StringConstantsOutOfMemoryError {

    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());// intern()方法将把字符串放到运行时常量池中
        }
    }
}