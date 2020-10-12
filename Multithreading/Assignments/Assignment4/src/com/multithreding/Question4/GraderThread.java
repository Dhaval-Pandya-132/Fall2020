package com.multithreding.Question4;

import java.io.IOException;

public class GraderThread implements Runnable {
    Utils ut;

    GraderThread(Utils ut) {
        this.ut = ut;
    }

    @Override // check the grade file periodically here
    public void run() {

        try {
            while (true) {
                Thread.sleep(20000);
                this.ut.readDatafromMap();
            }

//            Thread.currentThread().notify();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
