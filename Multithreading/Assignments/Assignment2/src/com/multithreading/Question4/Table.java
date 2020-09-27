package com.multithreading.Question4;

public class Table {
    static int  k=0;
    synchronized static void printTable(int n) {//synchronized method
        for (int i = 1; i <= 5; i++) {
            // System.out.println(this.toString());
            System.out.println(n * i);
            k++;
            System.out.println( "value of K = "+ k);

            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

     //   System.out.print("method ran successfully");
    }

}
