package com.multithreding.Question2;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        i.incrementAndGet();
        System.out.println(i);
    }
}
