package org.infinity.javabasics.concurrency;

public class StopThread {

    private static class ExecutorService {
        private Thread  executeThread;
        private boolean finished = false;

        public void execute(Runnable task) {
            executeThread = new Thread(() -> {
                Thread daemonThread = new Thread(task);

                daemonThread.setDaemon(true);// Set it as a daemon thread
                daemonThread.start();

                try {
                    daemonThread.join(); //父线程executeThread等待子线程daemonThread执行完后才继续执行
                    finished = true; // 执行到这里代表子线程daemonThread已经执行完
                } catch (InterruptedException e) {
                    System.out.println("父线程发起中断");// 子线程daemonThread由于是守护线程，其生命周期与父线程一致，父线程退出子线程也退出
                }
            }, "executeThread");

            executeThread.start();
        }

        public void shutdown(long timeout) {
            long currentTime = System.currentTimeMillis();
            while (!finished) {
                if (System.currentTimeMillis() - currentTime >= timeout) {
                    System.out.println("任务执行超时");
                    executeThread.interrupt();//父线程发起中断
                    break;
                }
            }
            finished = false;
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ExecutorService();
        long currentTime = System.currentTimeMillis();
        service.execute(() -> {
            while (true) {
                // 死循环模拟加载非常重的资源
            }
        });

        service.shutdown(5000);
        System.out.println(System.currentTimeMillis() - currentTime);
    }
}
