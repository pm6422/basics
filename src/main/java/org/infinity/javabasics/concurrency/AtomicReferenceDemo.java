package org.infinity.javabasics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AtomicReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> atomicUserReference = new AtomicReference<>(new Integer(0));

        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(x -> threadPool.execute(new Task(atomicUserReference)));
        threadPool.shutdown();

//        while (true) {
//            if (threadPool.isTerminated()) {
//                System.out.println(atomicUserReference.get());
//                break;
//            }
//        }

        if (threadPool.awaitTermination(2, TimeUnit.SECONDS)) {
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
