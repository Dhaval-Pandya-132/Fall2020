package com.multithreading.Question7;

public class Caller {
    public static void main(String[] args) throws InterruptedException {
        Input ip = new Input();
        Object lock = new Object();
        Thread t1 = new Thread(new MyThread(ip, lock), "Thread1");
        Thread t2 = new Thread(new MyThread(ip, lock), "Thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
