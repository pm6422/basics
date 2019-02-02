package org.infinity.javabasics.designpattern.observable;

// 定义了任务的生命周期中会被触发的接口
public interface TaskLifecycle<T> {

    // 启动触发
    void onStart(Thread thread);

    // 运行触发
    void onRunning(Thread thread);

    // 完成触发
    void onFinish(Thread thread, T result);

    // 异常触发
    void onError(Thread thread, Exception e);

    // 生命周期接口的空实现
    class ThreadLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {
            System.out.println("线程准备运行");
        }

        @Override
        public void onRunning(Thread thread) {
            System.out.println("线程正在运行");
        }

        @Override
        public void onFinish(Thread thread, T result) {
            System.out.println("线程运行完成，结果为:" + result);
        }

        @Override
        public void onError(Thread thread, Exception e) {
            System.out.println("线程出错");
        }
    }
}
