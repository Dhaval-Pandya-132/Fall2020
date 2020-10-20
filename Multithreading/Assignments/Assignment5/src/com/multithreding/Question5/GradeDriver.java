package com.multithreding.Question5;

import java.io.IOException;

public class GradeDriver {


    public static void main(String args[]) throws InterruptedException {

        Utils ut = new Utils();

        try {
            ut.createNewFile("Grade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread graderThread = new Thread(new GraderThread(ut), "GraderThread");
        graderThread.start();
        for (int i = 1; i <= 25; i++) {

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

    }


}
