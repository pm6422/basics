package org.infinity.javabasics.designpattern.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无synchronized或lock的单例版本
 * 由于静态内部类或者枚举方式都依赖于JVM的类加载机制，类加载时隐性的用到了synchronized，本例是无锁版本的单例
 */
public class CasSingleton {
    private static final AtomicReference<CasSingleton> INSTANCE = new AtomicReference<>();

    private CasSingleton() {
    }

    public static CasSingleton getInstance() {
        while (true) {
            CasSingleton singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new CasSingleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}