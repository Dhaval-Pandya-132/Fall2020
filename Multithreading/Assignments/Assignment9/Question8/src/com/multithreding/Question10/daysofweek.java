package com.multithreding.Question10;

public class daysofweek {

    native String[] returndays();

    static
    {
        System.load("/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment9/Question8/src/com/multithreding/Question10/libdaysforweek.so");//Linking the native library
    }

    static public void main(String args[])
    {

        daysofweek obj = new daysofweek();
        String[] days = obj.returndays();

        System.out.println(" > The days of the week are :: ");
        for(String name: days)
            System.out.println(name);
    }

}
