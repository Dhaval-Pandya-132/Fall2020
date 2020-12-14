package com.multithreding.Question6B;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class GraderStudentHandler implements Runnable {

    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    public GraderStudentHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {

        while (true) {
            Map<String, String> score = null;
            try {

                if (!s.isClosed() && dis != null) {


                    ObjectInputStream ois = new ObjectInputStream(dis);
                    if (ois != null) {
                        score = (Map) ois.readObject();

                        Utility ut = new Utility();
                        String grade = ut.calculateGrade(Integer.valueOf(score.get("homework")),
                                Integer.valueOf(score.get("midterm")),
                                Integer.valueOf(score.get("finalExam"))
                        );


                        ObjectOutputStream oos = new ObjectOutputStream(this.dos);
                        oos.writeObject(grade);
                        Student st = new Student(score.get("name"), Integer.valueOf(score.get("studentId")),
                                Integer.valueOf(score.get("homework")),
                                Integer.valueOf(score.get("midterm")),
                                Integer.valueOf(score.get("finalExam")),
                                grade
                        );
                        GraderServer.studentGradeCache.put(score.get("studentId"), st);
                        System.out.println("Total Student graded: " + GraderServer.studentGradeCache.size());
//                        s.close();
                        String output = "\n" + score.get("name") + "," + score.get("studentId")
                                + "," +
                                score.get("homework") + "," + score.get("midterm") + "," + score.get("finalExam")
                                + "," + grade;
                        if (score.get("requestType").equals("insert")) {

                            ut.writeDataToFile(new File("StudentGrade.txt"), output, true);


                        } else if (score.get("requestType").equals("update")) {

                            //  ut.findAndReplace("./StudentGrade.txt",score.get("name"),output);
                            List<String> lsOut = new ArrayList<>();

                            String content = "name,nextId,homework,midterm,final,Grade" ;
                            lsOut.add(content);
                            //  ut.writeDataToFile(new File("StudentGrade.txt"), content, false);

                            for (Map.Entry<String, Student> student : GraderServer.studentGradeCache.entrySet()) {
                                String updatedOutput = student.getValue().getStudentName()
                                        + "," + student.getValue().getStudentId()
                                        + "," + student.getValue().getHomework()
                                        + "," + student.getValue().getMidterm()
                                        + "," + student.getValue().getFinalExam()
                                        + "," + student.getValue().getFinalGrade() ;
                                //   ut.writeDataToFile(new File("StudentGrade.txt"), output, true);
                                lsOut.add(updatedOutput);

                            }
                            ut.findAndReplace("./StudentGrade.txt", lsOut);
                        }


                    }
                }

            } catch (IOException e) {
                // e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


    }
}
