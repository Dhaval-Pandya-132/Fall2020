package com.multithreding.Question5;

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
                Thread.sleep(25000);
                ut.readFile("Grade.txt");
            }

//            Thread.currentThread().notify();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
