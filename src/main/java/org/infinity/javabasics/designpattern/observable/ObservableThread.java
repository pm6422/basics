package org.infinity.javabasics.designpattern.observable;

public class ObservableThread<T> extends Thread implements Observable {

    // 定义了任务的生命周期中会被触发的接口
    private final TaskLifecycle lifecycle;
    // 任务
    private final Task          task;
    // 任务生命周期的枚举类型
    private       Cycle         cycle;

    // 构造方法 指定Task的实现，默认情况下使用EmptyLifecycle
    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.ThreadLifecycle(), task);
    }

    //构造方法  指定Task Lifecycle
    public ObservableThread(TaskLifecycle lifecycle, Task<T> task) {
        super();
        if (task == null)
            throw new IllegalArgumentException("The task is required.");
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public final void run() {
        //先是STARTED状态
        this.update(Cycle.STARTED, null, null);
        try {
            //RUNNING状态
            this.update(Cycle.RUNNING, null, null);
            //执行call方法
            T result = (T) this.task.call();
            //DONE状态
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            //ERROR状态
            this.update(Cycle.ERROR, null, e);
        }
    }

    @Override
    //得到线程状态
    public Cycle getCycle() {
        return this.cycle;
    }

    //根据线程不同状态来调用TaskLifecycle中定义的方法
    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }
}