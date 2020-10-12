package com.multithreding.Question6;

public class testReentrant {

    public static void main(String args[]) throws InterruptedException {
        Reentrant re = new Reentrant();
        re.outer();
    }


}
