package org.infinity.javabasics.designpattern.observable;

/**
 * 该接口用于返回线程的状态。其中枚举类定义了线程的四个状态。
 * start()方法与interrupt()方法用于屏蔽Thread类其他API。
 */
public interface Observable {

    // 任务生命周期的枚举类型
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    // 当前任务生命周期状态
    Cycle getCycle();

    //  启动 主要作用：屏蔽Thread的其他方法
    void start();

    // 定义线程打断方法 主要作用：屏蔽Thread的其他方法
    void interrupt();
}