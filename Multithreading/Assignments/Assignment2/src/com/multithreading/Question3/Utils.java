package com.multithreading.Question3;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Utils {

    public static int globalLineNumber = 1;
    public static ConcurrentHashMap<String, String> finalGradeList = new ConcurrentHashMap<String, String>();

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

    public void writeData(File file, String content) throws IOException, InterruptedException {
        synchronized (this) {

            FileWriter x = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(x);
            bw.write(content);
            bw.close();
            finalGradeList.put(Thread.currentThread().getName(), "None");
            this.notify();
            if (Thread.currentThread().getName().equals("Thread-25")) {
                this.wait(3000);
                this.notifyAll(); // at last thread notify
            }

//            this.wait(1000);
//            System.out.println("******* " + Thread.currentThread().getName() + " is notified now");
//            System.out.println("******* line number " + Utils.globalLineNumber);
//            System.out.println("Grade list :" + Utils.finalGradeList);

//            if (Utils.finalGradeList.get(Thread.currentThread().getName()) == "None") {
//                notify();
//                this.wait(100000);
//
//            }
//
            //         this.notifyAll();
        }


    }

    public void readFile(String name) throws IOException, InterruptedException {
        synchronized (this) {
            this.wait();
            File file = new File(name);

            int localLineNumber = 1;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                br.readLine(); // avoid firstLine
                while ((line = br.readLine()) != null ) {
                    if (localLineNumber <= globalLineNumber && line.length() >10) {
                        System.out.println("Line ---->" +line);
                        String[] data = line.split(",");
                        String Grade = this.calculateGrade(Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5].substring(0,1)));
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
            if (lines.size()-1 < LineNumber)
                return;
            String updatedElement = lines.get(LineNumber) + "," + Grade;
            lines.remove(LineNumber);
            lines.add(LineNumber, updatedElement);
            Files.write(path, lines, StandardCharsets.UTF_8);
        }

    }

}
