package com.multithreding.Question6;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class GraderThread implements Runnable {
    Utils ut;

    GraderThread(Utils ut) {
        this.ut = ut;
    }

    @Override // check the grade file periodically here
    public void run() {
        Thread.currentThread().setName("GraderThread");
        try {
            while (true) {
                Thread.sleep(3000);
                this.ut.readDatafromMap();
            }

//            Thread.currentThread().notify();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
