package com.assignment1.Question5;

public class MyThread implements Runnable {

    Input ip;
    Object lock;

    public MyThread(Input ip, Object lock){
        this.ip = ip;
        this.lock = lock;
    }

    @Override
    public void run() {
        int index = -1;
        while((index=ip.getIndex())!=-1){
            // synchronize block allow to run this code block by only on thread
            synchronized(lock) { // here we have used the instance lock
                System.out.println(
                        Thread.currentThread().getName());

                ip.print(index);
            }
        }
    }

}
