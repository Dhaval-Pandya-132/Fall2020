package com.multithreding.Question7;

import java.util.Map;
import java.util.concurrent.Callable;

public class Grader implements Callable {
    CallableUtils cu;

    public Grader(CallableUtils cu) {
        this.cu = cu;
    }

    @Override
    public Map<String, String> call() throws Exception {

        System.out.println("grader thread started");
     //   System.out.println("grader thread started "+ CallableUtils.isWrittenToFile);
        while (CallableUtils.isWrittenToFile == false) {
            Thread.sleep(2000);
            System.out.println("grader thread started "+ CallableUtils.isWrittenToFile);
            this.cu.readDataFromMapandWritetoFile();
        }
        return this.cu.readDataFromMapandWritetoFile();
    }
}
