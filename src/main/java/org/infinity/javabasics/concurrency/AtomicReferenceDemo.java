package org.infinity.javabasics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AtomicReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> atomicUserReference = new AtomicReference<>(new Integer(0));

        int threadPoolSize = 1000;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
        IntStream.range(0, threadPoolSize).forEach(x -> threadPool.execute(new Task(atomicUserReference)));
        threadPool.shutdown();

//        while (true) {
//            if (threadPool.isTerminated()) {
//                System.out.println(atomicUserReference.get());
//                break;
//            }
//        }

        // Blocks until all tasks have completed execution after a shutdown request
        if (threadPool.awaitTermination(1, TimeUnit.HOURS)) {
            System.out.println(threadPool.isTerminated());
            System.out.println(atomicUserReference.get());
        }

    }

    static class Task implements Runnable {
        private AtomicReference<Integer> ref;

        public Task(AtomicReference<Integer> ref) {
            this.ref = ref;
        }

        @Override
        public void run() {
            for (; ; ) {
                Integer oldV = ref.get();
                if (ref.compareAndSet(oldV, oldV + 1)) {
                    break;
                }
            }
        }
    }

    static class User {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
