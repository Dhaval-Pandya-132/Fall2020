package com.multithreding.Question6;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GradeDriver {


    public static void main(String args[]) throws InterruptedException {

        Utils ut = new Utils();

        try {
            ut.createNewFile("Grade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Thread graderThread = new Thread(new GraderThread(ut), "GraderThread");
//        graderThread.start();
        ThreadPoolExecutor tpegrader = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        tpegrader.execute(new GraderThread(ut));
        ThreadPoolExecutor tpeStudent = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);

        for (int i = 1; i <= 50; i++) {

            Student st = new Student(ut);
//            Thread studentThread = new Thread(st, "Thread-" + st.getId());
//            studentThread.start();

            tpeStudent.execute(st);


//            try {
//                studentThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            System.out.println(st);
        }
        tpeStudent.shutdown();
        tpegrader.shutdown();
//        tpe.awaitTermination()

    }


}
