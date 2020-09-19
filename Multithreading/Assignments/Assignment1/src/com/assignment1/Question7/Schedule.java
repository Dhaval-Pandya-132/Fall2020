package com.assignment1.Question7;

import java.util.Set;

class Schedule implements Runnable {
    private static Thread [] jobs = new Thread[4];
    private int threadID;
    public Schedule(int ID) {
        threadID = ID;
    }
    public void run() { // do something
        System.out.println(
                Thread.currentThread().getName());
         }
    public static void  main(String [] args) {
        int nextThread = 0;
//        Thread.setPriority(Thread.MAX_PRIORITY);
        for(int i=0; i<jobs.length; i++) {
            jobs[i] = new Thread(new Job(i));
            jobs[i].setPriority(Thread.MIN_PRIORITY); // initially set the min priority
            jobs[i].start();
        }
////        System.out.println("Active thread count "+Thread.activeCount());
//        Set<Thread> threads = Thread.getAllStackTraces().keySet();
//        System.out.println("Active thread count "+threads);
        try {
            for(;;) {
                System.out.println("Inside for loop -->"+ jobs[nextThread].getName());
                jobs[nextThread].setPriority(Thread.NORM_PRIORITY);
                Thread.sleep(1000);
                jobs[nextThread].setPriority(Thread.MIN_PRIORITY);
                nextThread = (nextThread + 1) % jobs.length;
            }

        } catch(InterruptedException e) { System.out.println(e); }
    }
}

