package com.multithreding.Question6B;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraderServer {
    public static Map<String,Student> studentGradeCache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5057);
        Utility ut = new Utility();

        ut.createNewFile("StudentGrade.txt");
        System.out.println("Grader Server starts on " + ss.getLocalPort() + " port.");
        System.out.println("Grader Server starts on " + ss.getInetAddress() + " port.");


        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); // To Write
                DataInputStream dis = new DataInputStream(s.getInputStream()); //To Read


                System.out.println("Assigning new Student handler thread ");
                Thread t = new Thread(new GraderStudentHandler(s,dis,dos));

                t.start();


  //              System.out.println("Total Student Graded :"+ studentGradeCache.size());
                //System.out.println("object input stream :" +(Map)ois.readObject());
//                Map<String, Integer> score = (Map) ois.readObject();
//
//                String grade = ut.calculateGrade(score.get("homework"),
//                        score.get("midterm"),
//                        score.get("finalExam")
//                );
//                dos.writeUTF(grade);

                // create a new thread object
                //   Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                // t.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}
