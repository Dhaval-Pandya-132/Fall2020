package com.multithreding.Question10;

import java.util.Scanner;

public class reverseString {
    native String reversefunc(String word);

    static
    {
        System.load("/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment9/Question8/src/com/multithreding/Question10/libreverseString.so");//Linking the native library
    }

    public static void main(String args[])
    {
        Scanner inp = new Scanner(System.in);

        System.out.println(" > Enter a string :: ");
        String word = inp.nextLine();

        reverseString obj = new reverseString();

        System.out.println(" > The reversed string is :: "+obj.reversefunc(word));
    }
}
