package com.assignment1.Question2;

import com.assignment1.Question2.student;

public class TestDriver {

    public static void main(String args[])
    {
        int max=100;
        int min=70;
        for(int i = 1 ; i <=20 ;i++)
        {
            student stud = new student(i);
            stud.setId(i);
            stud.setMidterm((int)(Math.random() * (max - min + 1) + min));
            stud.setFinalExam((int)(Math.random() * (max - min + 1) + min));
            stud.setHomework((int)(Math.random() * (max - min + 1) + min));
            System.out.println(stud);

        }




    }


}
