package com.assignment1.Question6;

public class main {

    public static void main(String args[])
    {
        Runnable task = () ->{
          String threadName = Thread.currentThread().getName();
          System.out.println("Hello "+ threadName);
        };
        task.run(); // task run in main thread
        Thread thread= new  Thread(task); // now we are running same task with different thread
        thread.start();
        System.out.println("Done !");
    }


}
