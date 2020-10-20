package com.multithreding.Question7;


import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class Student implements Callable {

    private String name;
    private int id, homework, midterm, finalExam;
    private String finalGrade;
    private static int nextId = 1;
    int max = 100;
    int min = 70;
    CallableUtils ut;

    public Student(CallableUtils ut) {
        this.name = "Student" + Student.nextId;
        this.id = Student.nextId;
        Student.nextId = ++Student.nextId;
        this.ut = ut;
    }


    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public Student call() throws Exception {

        Integer homework = (int) (Math.random() * (max - min) + min);
        Integer midterm = (int) (Math.random() * (max - min) + min);
        Integer finalExam = (int) (Math.random() * (max - min) + min);
        String[] output = this.ut.saveScore(homework, midterm, finalExam);
        System.out.println("Output of  " + Thread.currentThread().getName() + " " + output.length);
        if (output.length == 8) {

            this.setName(output[0]);
            this.setId(Integer.valueOf(output[1]));
            this.setHomework(Integer.valueOf(output[3]));
            this.setMidterm(Integer.valueOf(output[4]));
            this.setFinalExam(Integer.valueOf(output[5]));
           // this.setFinalGrade(output[6]);
        }
        return this;
    }
}