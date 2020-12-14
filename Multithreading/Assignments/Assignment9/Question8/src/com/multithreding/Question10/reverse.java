package com.multithreding.Question10;

import java.util.Scanner;

public class reverse {
    native String reversefunc(String word);

    static
    {
        System.load("/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment9/Question8/src/com/multithreding/Question10/libreverse.so");
    }

    public static void main(String args[])
    {
        Scanner inp = new Scanner(System.in);

        System.out.println(" > Enter a string :: ");
        String word = inp.nextLine();

        reverse obj = new reverse();

        System.out.println(" > The reversed string is :: "+obj.reversefunc(word));
    }
}
