package com.multithreding.Question4;

import javax.sound.midi.SysexMessage;
import java.awt.print.PrinterGraphics;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Utils {

    public static int globalLineNumber = 1;
    public static ConcurrentHashMap<String, String> finalGradeList = new ConcurrentHashMap<String, String>();
    public static ConcurrentHashMap<String, List<Integer>> finalGradeMap =
            new ConcurrentHashMap<String, List<Integer>>();
    public static Map<String, Boolean> processedStudentList = new HashMap<>();

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
        synchronized (this) {
            while (index < 3) {

                int score = (int) (Math.random() * (max - min) + min);
                al.add(index, score);
                // System.out.println(Thread.currentThread().getName() + " ---> " + al);
                finalGradeMap.put(Thread.currentThread().getName(), al);
                index++;
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.notifyAll();
            }
            this.notifyAll();
            this.wait(5000);
            return this.readDataFromFile("Grade.txt");
        }
    }


    public void readDatafromMap() throws InterruptedException, IOException {

        System.out.println(Thread.currentThread().getName() + " is processing map");
        synchronized (this) {

            if (processedStudentList.size() == 40) {
                this.wait(10000);
                this.notifyAll();
                Thread.currentThread().stop();
                return;
            }

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
                    this.wait(2000);
                    this.notifyAll();
                }

            }


        }
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

        String[] data = {};
        synchronized (this) {

            File file = new File(fileName);
            String threadName = Thread.currentThread().getName();
            int localLineNumber = 1;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                br.readLine(); // avoid firstLine
                while ((line = br.readLine()) != null) {

                        data = line.split(",");
                        if (data.length == 7 && threadName.equals(data[2])) {
                            System.out.println(threadName + " " + "is reading this line");
                            return data;
                        }
                        else
                        {
                            this.wait(3000);
                            this.notifyAll();
                        }
                }
                this.notifyAll();
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    return data;
    }


    public void readFile(String name) throws IOException, InterruptedException {
        synchronized (this) {
            this.wait();
            File file = new File(name);

            int localLineNumber = 1;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                br.readLine(); // avoid firstLine
                while ((line = br.readLine()) != null) {
                    if (localLineNumber <= globalLineNumber && line.length() > 10) {
                        System.out.println("Line ---->" + line);
                        String[] data = line.split(",");
                        String Grade = this.calculateGrade(Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5].substring(0, 1)));
                        finalGradeList.put(data[2], Grade);
                        this.updateFile("Grade.txt", Utils.globalLineNumber, Grade);
                        System.out.println(Thread.currentThread().getName() + " -->" + "fetching " + globalLineNumber + "--->" + line + " " + Grade);
                        globalLineNumber++;
                    }
                    localLineNumber++;
                }
                globalLineNumber = localLineNumber;
                this.notifyAll();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //  return false;
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

    public void updateFile(String FileName, int LineNumber, String Grade) throws IOException, InterruptedException {
        synchronized (this) {

            Path path = Paths.get(FileName);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            if (lines.size() - 1 < LineNumber)
                return;
            String updatedElement = lines.get(LineNumber) + "," + Grade;
            lines.remove(LineNumber);
            lines.add(LineNumber, updatedElement);
            Files.write(path, lines, StandardCharsets.UTF_8);
        }

    }
}