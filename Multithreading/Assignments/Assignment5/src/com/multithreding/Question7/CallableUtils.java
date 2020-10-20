package com.multithreding.Question7;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class CallableUtils {

    ConcurrentHashMap<String, List<Integer>> studentAvgScore = new ConcurrentHashMap<>();
    public static Map<String, String> processedStudentList = new HashMap<>();
    public static boolean isWrittenToFile = false;

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


    public String[] saveScore(int homeWork, int midterm, int finalExam) throws InterruptedException {

        List<Integer> li = new ArrayList<>();
        String[] output={};
        synchronized (this) {
            li.add(0, homeWork);
            li.add(1, midterm);
            li.add(2, finalExam);
            String threadName = Thread.currentThread().getName();
            studentAvgScore.put(threadName, li);
//            this.notifyAll();
            System.out.println("Thread string name :" + threadName );
            this.wait(5000);
//            System.out.println(threadName + " is resuming");
            try {
                return readDataFromFile("callableGrade.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;

    }


    public Map<String,String> readDataFromMapandWritetoFile() throws IOException {
        String threadName = Thread.currentThread().getName();

        if(processedStudentList.size() == studentAvgScore.size()&& studentAvgScore.size() != 0)
        {
            isWrittenToFile=true;
            return processedStudentList;
        }
        System.out.println("processing entrySet "+ studentAvgScore.size());
        synchronized (this) {
            System.out.println("processing entrySet"+ studentAvgScore.size());
            if(studentAvgScore.size() == 0){
                try {
                    this.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (Map.Entry<String, List<Integer>> entrySet : studentAvgScore.entrySet()) {

                if (!processedStudentList.containsKey(entrySet.getKey())) {
                    List<Integer> li = entrySet.getValue();
                    String nextId = entrySet.getKey().split("-")[1];
                    String name = "student-" + entrySet.getKey().split("-")[1];
                    Integer homework = li.get(0);
                    Integer midterm = li.get(1);
                    Integer finalExam = li.get(2);
                    Integer avgscore = (homework + midterm + finalExam) / 3;
                    String letterGrade = calculateGrade(avgscore);
                    String output ="\n" + name + "," + nextId + "," + entrySet.getKey() + "," + homework + ","
                            + midterm + "," + finalExam + "," + avgscore+","+letterGrade;

                    writeDataToFile(new File("callableGrade.txt"), output);
                    processedStudentList.put(entrySet.getKey(), letterGrade);
                }
                else
                {

                    try {

                        this.wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.notifyAll();
                }
            }

        }

        return processedStudentList;
    }


    public void writeDataToFile(File file, String content) throws IOException {
        synchronized (this) {

            FileWriter x = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(x);
            bw.write(content);
            bw.close();

        }
    }


    public String[] readDataFromFile(String fileName) throws IOException {
        //     System.out.println(Thread.currentThread().getName() + " is reading  data from file");
        String[] data = {};
        synchronized (this) {
            File file = new File(fileName);
            String threadName = Thread.currentThread().getName();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                //    System.out.println(threadName + " inside buffer reader :");
                br.readLine(); // avoid firstLine
                if (data.length == 0) {
                    try {
                        this.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while ((line = br.readLine()) != null) {

                        data = line.split(",");

//                        System.out.println(threadName +" is reading line : "+ line);
                        if (data.length == 8 && threadName.equals(data[2])) {
//                              System.out.println(threadName + " " + "is reading this line");
                            return data;
                        } else {

                            try {
                                this.wait(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            this.notifyAll();

                        }
                    }
                }

               this.notifyAll();
//                con.signalAll();
            }


        }
        return data;
    }


    public String calculateGrade(Integer avgScore) {

        if (avgScore >= 90) {
            return "A";
        } else if (avgScore >= 80 && avgScore < 90) {
            return "B";
        } else if (avgScore >= 70 && avgScore < 80) {
            return "C";
        } else if (avgScore >= 60 && avgScore < 70) {
            return "D";
        } else {
            return "F";
        }

    }


}
