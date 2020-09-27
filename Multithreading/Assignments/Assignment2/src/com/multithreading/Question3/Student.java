package com.multithreading.Question3;

import java.io.File;
import java.io.IOException;

public class Student implements Runnable {

    private String name;
    private int id, homework, midterm, finalExam;
    private static int nextId = 1;
    int max = 100;
    int min = 70;
    Utils ut;

    public Student(Utils ut) {
        this.name = "Student" + Student.nextId;
        this.id = Student.nextId;
        Student.nextId = ++Student.nextId;
        this.ut=ut;
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
                '}';
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        this.setMidterm((int) (Math.random() * (max - min ) + min));
        this.setFinalExam((int) (Math.random() * (max - min ) + min));
        this.setHomework((int) (Math.random() * (max - min ) + min));
        try {
            String threadName = Thread.currentThread().getName();
            String output = this.getName() + "," + Student.getNextId() + "," + threadName + "," + this.getHomework() + "," +
                    this.getMidterm() + "," + this.finalExam;
//            System.out.println(Thread.currentThread().getName() +" is now waiting");
            this.ut.writeData(new File("Grade.txt"), "\n" + output);
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
