package org.infinity.javabasics.utils;

import java.lang.management.ManagementFactory;

public class Utils {

    private Utils() {

    }

    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // get pid
        return name.split("@")[0];
    }
}
