package com.multithreding.Question6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Utils {

    public static ConcurrentHashMap<String, String> finalGradeList = new ConcurrentHashMap<String, String>();
    public static ConcurrentHashMap<String, List<Integer>> finalGradeMap =
            new ConcurrentHashMap<String, List<Integer>>();
    public static Map<String, Boolean> processedStudentList = new HashMap<>();
    public final Lock lock = new ReentrantLock();
    public final Condition con = lock.newCondition();


    public void createNewFile(String name) throws IOException {
        String content = "name,nextId,threadId,homework,midterm,final,Grade";

        File file = new File(name);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter x = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(x);

        bw.write(content);
        bw.close();
    }

    public String[] addScoreToList() throws IOException, InterruptedException {
        int index = 0;
        List<Integer> al = new ArrayList<>();
        int max = 100;
        int min = 70;
//        synchronized (this) {

        lock.lock();
        //     System.out.println("addScoreToList inside lock--->" + Thread.currentThread().getName());
        try {

            while (index < 3) {

                int score = (int) (Math.random() * (max - min) + min);
                al.add(index, score);
//                System.out.println(Thread.currentThread().getName() + " index ---> " + index);
//                System.out.println(Thread.currentThread().getName() + " ---> " + al);
                finalGradeMap.put(Thread.currentThread().getName(), al);
                index++;
                try {
//                        this.wait(1000);
                    con.await(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                    this.notifyAll();
                con.signalAll();
            }
//                this.notifyAll();
            con.signalAll();
//                this.wait(5000);
            con.await(5000, TimeUnit.MILLISECONDS);
            //       System.out.println(Thread.currentThread().getName() + " will read data ");

//            if(this.readDataFromFile("Grade.txt").length == 0){
//                Thread.sleep(10000);
//            }
            return this.readDataFromFile("Grade.txt");

        } finally {

            lock.unlock();
            //         System.out.println("addScoreToList released lock--->" + Thread.currentThread().getName());
        }

//        }
    }


    public void readDatafromMap() throws InterruptedException, IOException {

//        synchronized (this) {
        lock.lock();
        try {
            if (processedStudentList.size() == 40) {
//                this.wait(10000);
                con.await(10000, TimeUnit.MILLISECONDS);
//                this.notifyAll();
                con.signalAll();
                Thread.currentThread().stop();
                return;
            }

            System.out.println(Thread.currentThread().getName() + " is processing map");
            for (Map.Entry<String, List<Integer>> stu :
                    finalGradeMap.entrySet()) {
                List<Integer> al = stu.getValue();
                System.out.println(stu.getKey() + " " + al);
                if (!processedStudentList.containsKey(stu.getKey())) {
                    if (al.size() >= 3 && al.get(2) > 0) {
                        String threadName = stu.getKey();
                        String StudentName = "Student-" + threadName.split("-")[1];
                        String nextId = threadName.split("-")[1];
                        Integer homework = al.get(0);
                        Integer midterm = al.get(1);
                        Integer finalterm = al.get(2);
                        String finalGrade = this.calculateGrade(homework, midterm, finalterm);
                        String output = "\n" + StudentName + "," + nextId + "," + threadName + "," +
                                homework + "," + midterm + "," + finalterm + "," + finalGrade;
                        this.writeDataToFile(new File("Grade.txt"), output);
                        processedStudentList.put(threadName, true);
                        //   this.notifyAll();
                    }
                } else {
//                    this.wait(2000);
                    con.await(2000, TimeUnit.MILLISECONDS);
//                    this.notifyAll();
                    con.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }


//        }
    }

    public void writeDataToFile(File file, String content) throws IOException {
//        synchronized (this) {
//
        lock.lock();
        try {
            FileWriter x = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(x);
            bw.write(content);
            bw.close();
        } finally {
            lock.unlock();
        }
    }


    public String[] readDataFromFile(String fileName) throws IOException {
        //     System.out.println(Thread.currentThread().getName() + " is reading  data from file");
        String[] data = {};
//        synchronized (this) {
        lock.lock();
        try {
            File file = new File(fileName);
            String threadName = Thread.currentThread().getName();
            int localLineNumber = 1;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                //    System.out.println(threadName + " inside buffer reader :");
                br.readLine(); // avoid firstLine
                if (data.length == 0) {
                    con.await(10000, TimeUnit.MILLISECONDS);
                    while ((line = br.readLine()) != null) {

                        data = line.split(",");

                        if (data.length == 7 && threadName.equals(data[2])) {
                            //  System.out.println(threadName + " " + "is reading this line");
                            return data;
                        } else {
//                        this.wait(3000);
                            con.signalAll();
                            con.await(5000, TimeUnit.MILLISECONDS);
//                        this.notifyAll();

                        }
                    }
                }

//                this.notifyAll();
                con.signalAll();
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            lock.unlock();
        }

        return data;
    }


    public String calculateGrade(int homeWork, int midTerm, int finalExam) {

        int overAllScore = (int) (homeWork * 0.5 + midTerm * 0.3 + finalExam * 0.2);
        if (overAllScore >= 90) {
            return "A";
        } else if (overAllScore >= 80 && overAllScore < 90) {
            return "B";
        } else if (overAllScore >= 70 && overAllScore < 80) {
            return "C";
        } else if (overAllScore >= 60 && overAllScore < 70) {
            return "D";
        } else {
            return "F";
        }

    }

}