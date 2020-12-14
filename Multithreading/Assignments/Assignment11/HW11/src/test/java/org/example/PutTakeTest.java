package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutTakeTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;

    public static void main(String[] args) {
        new PutTakeTest(10, 10, 100000).test(); // sample parameters
        pool.shutdown();
        System.exit(0);
    }

    PutTakeTest(int capacity, int npairs, int ntrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }


    public void test() {
     //   new PutTakeTest(10, 10, 100000);
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
               pool.execute(new Consumer());
            }
            barrier.await(); // wait for all threads to be ready barrier.await(); // wait for all threads to finish
            System.out.println("assertEquals");
            // throw new Exception();
            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* inner classes of PutTakeTest (Listing 12.5) */
     class Producer implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int)System.nanoTime()); int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                } putSum.getAndAdd(sum); barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

     class Consumer implements Runnable {
        public void run() {
            try { barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take(); }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
