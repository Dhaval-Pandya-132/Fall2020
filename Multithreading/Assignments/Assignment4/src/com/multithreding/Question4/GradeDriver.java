package com.multithreding.Question4;

import java.io.IOException;

public class GradeDriver {


    public static void main(String args[]) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        Utils ut = new Utils();

        try {
            ut.createNewFile("Grade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread graderThread = new Thread(new GraderThread(ut), "GraderThread");
        graderThread.start();
        for (int i = 1; i <= 150; i++) {

            Student st = new Student(ut);
            Thread studentThread = new Thread(st, "Thread-" + st.getId());
            studentThread.start();
            try {
                studentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // graderThread.join();
            System.out.println(st);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Execution Time : "+ elapsedTime + " ms");
    }


}
