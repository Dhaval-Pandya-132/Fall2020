package com.multithreding.Question4;

public class JavaNonAtomic {
    public static void main(String[] args) throws InterruptedException {

        ProcessingThreadNonAutomic pt = new ProcessingThreadNonAutomic();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}
