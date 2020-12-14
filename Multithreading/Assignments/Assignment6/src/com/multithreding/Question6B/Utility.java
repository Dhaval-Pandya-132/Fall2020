package com.multithreding.Question6B;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    public void createNewFile(String name) throws IOException {
        String content = "name,nextId,homework,midterm,final,Grade";

        File file = new File(name);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter x = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(x);

        bw.write(content);
        bw.close();
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

    public void writeDataToFile(File file, String content, boolean isAppend) throws IOException {
        synchronized (this) {
            FileWriter x = new FileWriter(file, isAppend);
            BufferedWriter bw = new BufferedWriter(x);
            bw.write(content);
            bw.close();
        }
    }

    public void findAndReplace(String pathName,List<String> lsData) {
        synchronized (this){
            try {
                Path path = Paths.get(pathName);
//                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
////            lines.set(lineNumber - 1, data);
//                List<String>  lsData=lines.parallelStream()
//                        .filter(std -> !std.startsWith(studentId))
//                        .collect(Collectors.toList());
//
//                lsData.add(data);
//                System.out.println("lsddata: " +lsData);
                Files.write(path, lsData, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }


    }

}
