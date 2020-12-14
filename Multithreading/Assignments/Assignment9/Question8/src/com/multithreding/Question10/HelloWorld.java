package com.multithreding.Question10;

public class HelloWorld {
    public native void cfunction();//Declaring the native function
    static
    {
        System.load("/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment9/Question8/src/com/multithreding/Question10/libforhelloworld.so");//Linking the native library
    }                                      //which we will be creating.

    public static void main(String args[])
    {

        HelloWorld obj = new HelloWorld();
        obj.cfunction();//Calling the native function
    }
}
