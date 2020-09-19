package com.assignment1.Question5;

public class Caller {
    public static void main(String[] args)
            throws InterruptedException {
        Input ip = new Input();
        Object lock = new Object();
        Thread t1 = new Thread(new MyThread(ip, lock), "Thread1");
        Thread t2 = new Thread(new MyThread(ip, lock), "Thread2");
        t1.start(); // start t1 thread
        t2.start(); // start t2 thread
        t1.join(); // Main thread is wait till this t1 get finished
        t2.join(); // Main thread is wait till this t2 get finished
    }

}
