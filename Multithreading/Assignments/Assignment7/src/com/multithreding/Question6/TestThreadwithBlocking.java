package com.multithreding.Question6;

import java.util.concurrent.atomic.AtomicLong;

public class TestThreadwithBlocking {
    static class Counter {
        private long c = 0;

        public synchronized void increment() {
            c++;
        }

        public synchronized long value() {
            return c;
        }
    }

    public static void main(final String[] arguments) throws InterruptedException {
        final TestThreadwithBlocking.Counter counter = new TestThreadwithBlocking.Counter();
        long start = System.currentTimeMillis();
        //1000 threads
        for(int i = 0; i < 1000000 ; i++) {

            new Thread(new Runnable() {

                public void run() {
                    counter.increment();
                }
            }).start();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("elapsed time in milliseconds " + time);
        // Thread.sleep(6000);
        System.out.println("Final number (should be 1000): " + counter.value());
    }

}
