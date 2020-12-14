package com.multithreding.Question6B;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class StudentMain {

    public static void main(String args[]) throws IOException {

        for (int i = 0; i < 40; i++) {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5057);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Student stu = new Student(dos, dis, s);
            Thread t = new Thread(stu, "Thread-" + stu.getStudentId());
            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }


//        System.out.println("is socket closed : "+ s.isClosed());
//        ObjectInputStream oi = new ObjectInputStream(ois);
//        System.out.println("reading in main " + oi.readUTF());
//        ois.close();

//        }


    }

}
