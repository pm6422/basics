package org.infinity.javabasics.designpattern.observable;

@FunctionalInterface
public interface Task<T> {

    // 任务执行接口，允许有返回值
    T call();
}