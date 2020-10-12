package com.multithreding.Question6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {
    int level = 0;
    Lock lock = new ReentrantLock();


    public void outer() throws InterruptedException {
        lock.lock();
        inner();
        lock.unlock();
    }

    public synchronized void  inner() throws InterruptedException {
        level++;
        lock.lock();
        try {
            System.out.println("level-->"+ level);
            if (level <= 3) {
                inner();
                if (level == 2) {
                    Thread.sleep(1000);
                }
            } else {
                Thread.sleep(1000);
            }
        } finally {
            lock.unlock();
            level--;
            System.out.println("final level-->"+ level);
        }
    }

}
