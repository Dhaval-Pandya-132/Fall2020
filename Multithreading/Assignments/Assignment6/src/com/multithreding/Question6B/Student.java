package com.multithreding.Question6B;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Student implements Runnable {

    private String name;
    private int id, homework, midterm, finalExam;
    private Map<String, String> score = new HashMap<>();
    private String finalGrade;
    private DataOutputStream dos;

    private DataInputStream dis;
    private Socket s;
    private static int nextId = 1;
    int max = 100;
    int min = 70;

    public Student(DataOutputStream dos, DataInputStream ois, Socket s) {
        this.dos = dos;
        this.dis = ois;
        this.s = s;
        this.name = "Student" + Student.nextId;
        this.id = Student.nextId;
        Student.nextId = ++Student.nextId;
    }

    public Student(String name, int id, int homework, int midterm, int finalExam, String finalGrade) {
        this.name = name;
        this.id = id;
        this.homework = homework;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.finalGrade = finalGrade;
    }

    public String getStudentName() {
        return name;
    }

    public void setStudentName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return id;
    }

    public void setStudentId(int id) {
        this.id = id;
    }

    public int getHomework() {
        return homework;
    }

    public void setHomework(int homework) {
        this.homework = homework;
    }

    public int getMidterm() {
        return midterm;
    }

    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    public int getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(int finalExam) {
        this.finalExam = finalExam;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", homework=" + homework +
                ", midterm=" + midterm +
                ", finalexam=" + finalExam +
                ", Grade=" + finalGrade +
                '}';
    }

    @Override
    public void run() {

        System.out.println("Inside run method");
        Integer homework = (int) (Math.random() * (max - min) + min);
        Integer midterm = (int) (Math.random() * (max - min) + min);
        Integer finalExam = (int) (Math.random() * (max - min) + min);
        this.setHomework(homework);
        this.setMidterm(midterm);
        this.setFinalExam(finalExam);
        score.put("requestType","insert");
        score.put("studentId", String.valueOf(this.getStudentId()));
        score.put("name", String.valueOf(this.getStudentName()));
        score.put("firstName","FirstName-" + this.getStudentId());
        score.put("firstName","LastName-" + this.getStudentId());
        score.put("homework", homework.toString());
        score.put("midterm", midterm.toString());
        score.put("finalExam", finalExam.toString());
        try {

           //
            while (true) {
                ObjectOutputStream os = new ObjectOutputStream(this.dos);
                System.out.println(Thread.currentThread().getName() + " is submitting score");
                os.writeObject(score);
                System.out.println(Thread.currentThread().getName() + " is Reading grade");

                ObjectInputStream oi = new ObjectInputStream(this.dis);
                String grade =(String)oi.readObject();
                this.setFinalGrade(grade);
                System.out.println("grade :" + this);
                Thread.sleep(5000);


                if(this.getStudentId() ==5 || this.getStudentId() ==10 || this.getStudentId() ==15 )
                {
                    System.out.println(this.getStudentName() + " requesting for score update");
                    homework = (int) (Math.random() * (max - min) + min);
                    midterm = (int) (Math.random() * (max - min) + min);
                    finalExam = (int) (Math.random() * (max - min) + min);
                    this.setHomework(homework);
                    this.setMidterm(midterm);
                    this.setFinalExam(finalExam);
                    score.put("requestType", "update");
                    score.put("homework", homework.toString());
                    score.put("midterm", midterm.toString());
                    score.put("finalExam", finalExam.toString());
                    ObjectOutputStream osupdate = new ObjectOutputStream(this.dos);
                    osupdate.writeObject(score);

                    ObjectInputStream oiupdate = new ObjectInputStream(this.dis);
                    grade = (String) oiupdate.readObject();
                    this.setFinalGrade(grade);

                    System.out.println(this.getStudentName() + " updated Grade value is " + grade);

                }

                if(grade != ""){

                    dos.close();
                    dis.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
