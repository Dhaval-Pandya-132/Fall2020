package com.multithreding.Question7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class main {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        List<Future<Student>> resultList = new ArrayList<>();
        CallableUtils ut = new CallableUtils();

        try {
            ut.createNewFile("callableGrade.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        FutureTask<Map<String, String>> GraderFutureTask = new FutureTask<Map<String, String>>(new Grader(ut));
        Thread graderThread = new Thread(GraderFutureTask, "GraderThread");
        graderThread.start();


        //  FutureTask[] ft = new FutureTask[30];

        for (int i = 0; i < 30; i++) {

            Student callable = new Student(ut);

        //    callable.setFinalGrade(GraderFutureTask.get().get("Thread-" + i));
            // Create the FutureTask with Callable
            FutureTask<Student> ft = new FutureTask(callable);

            // As it implements Runnable, create Thread
            // with FutureTask
            Thread t = new Thread(ft);
            t.start();
            resultList.add(ft);

        }
       // graderThread.join();
        System.out.println("Grader thread future task "+GraderFutureTask.get());

        for (int i = 0; i < 30; i++) {
            System.out.println("printing student data using Future Task");
            resultList.get(i).get().setFinalGrade(GraderFutureTask.get().get("Thread-" + i));
            System.out.println(resultList.get(i).get());
        }


    }

}
