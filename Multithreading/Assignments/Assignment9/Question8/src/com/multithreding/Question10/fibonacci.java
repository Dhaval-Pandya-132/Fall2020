package com.multithreding.Question10;

import java.util.Scanner;

public class fibonacci {

    native int[] returnfibo(int n);

    static
    {
        System.load("/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment9/Question8/src/com/multithreding/Question10/libfibonacci.so");//Linking the native library
    }

    public static void main(String args[])
    {
        Scanner inp = new Scanner(System.in);

        System.out.println(" > Enter n :: ");
        int n = inp.nextInt();

        fibonacci obj = new fibonacci();
        int[] Fibo = obj.returnfibo(n);

        System.out.println(" > The first "+n+" fibonacci numbers are :: ");

        for(int i=0;i<n;i++)
            System.out.print(Fibo[i]+",");
    }
}
