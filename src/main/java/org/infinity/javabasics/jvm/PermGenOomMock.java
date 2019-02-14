package org.infinity.javabasics.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Options: -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8M
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("org.infinity.javabasics.jvm.Employee");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}