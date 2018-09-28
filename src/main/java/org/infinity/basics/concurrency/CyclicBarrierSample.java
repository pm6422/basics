package org.infinity.basics.concurrency;

public class CyclicBarrierSample {

}

//class Solver {
//    final int           N;
//    final float[][]     data;
//    final CyclicBarrier barrier;
//
//    class Worker implements Runnable {
//        int myRow;
//
//        Worker(int row) {
//            myRow = row;
//        }
//
//        public void run() {
//            while (!done()) {
//                processRow(myRow);
//
//                try {
//                    barrier.await();
//                } catch (InterruptedException ex) {
//                    return;
//                } catch (BrokenBarrierException ex) {
//                    return;
//                }
//            }
//        }
//    }
//
//    public Solver(float[][] matrix) {
//        data = matrix;
//        N = matrix.length;
//        Runnable barrierAction =
//                new Runnable() {
//                    public void run() {
//                        mergeRows();
//                    }
//                };
//        barrier = new CyclicBarrier(N, barrierAction);
//
//        List<Thread> threads = new ArrayList<Thread>(N);
//        for (int i = 0; i < N; i++) {
//            Thread thread = new Thread(new Worker(i));
//            threads.add(thread);
//            thread.start();
//        }
//
//        // wait until done
//        for (Thread thread : threads)
//            thread.join();
//    }
//}
