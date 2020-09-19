package com.assignment1.Question2;

public class student {

    String name;
    int id , homework ,midterm, finalExam;
    static int nextId =1;


    public student(int id)
    {
        this.nextId= id;
        this.name = "Student_"+ this.nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private double getOverallScore()
    {
        return this.homework * 0.5 + this.midterm* 0.3 +this.finalExam *0.2;
    }

    private String calculateGrade()
    {
        double overAllScore =getOverallScore();
        if (overAllScore >= 94)
        {
            return "A";
        }
        else if(overAllScore >=90 && overAllScore < 94)
        {
            return "B";
        }
        else if(overAllScore >=86 && overAllScore < 90)
        {
            return "C";
        }
        else if(overAllScore >=80 && overAllScore < 85)
        {
            return "D";
        }
        else
        {
            return "F";
        }

    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", homework=" + homework +
                ", midterm=" + midterm +
                ", final =" + finalExam +
                ", letterGrade =" + calculateGrade() +
                '}';
    }
}
