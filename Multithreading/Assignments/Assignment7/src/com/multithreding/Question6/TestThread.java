package com.multithreding.Question6;

import java.util.concurrent.atomic.AtomicLong;

public class TestThread {
    static class Counter {
        private AtomicLong c = new AtomicLong(0);

        public void increment() {
            c.getAndIncrement();
        }

        public long value() {
            return c.get();
        }
    }

    public static void main(final String[] arguments) throws InterruptedException {
        final Counter counter = new Counter();
        long start = System.currentTimeMillis();

        //1000 threads
        for(int i = 0; i < 1000000 ; i++) {

            new Thread(new Runnable() {

                public void run() {
                    counter.increment();
                }
            }).start();
        }

       // Thread.currentThread().join();
        long time = System.currentTimeMillis() - start;
        System.out.println("elapsed time in milliseconds " + time);
        //  Thread.sleep(6000);
        System.out.println("Final number (should be 1000): " + counter.value());
    }


}
/**
 * elapsed time in milliseconds 37167
 * Final number (should be 1000): 1000000
 **/