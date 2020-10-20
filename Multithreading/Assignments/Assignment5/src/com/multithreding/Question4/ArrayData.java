package com.multithreding.Question4;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ArrayData implements Runnable {

    int[][] array;
    private static int rowCount = 0;

    public ArrayData(int[][] array) {
        this.array = array;
    }

    //
    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        System.out.println(name + " is running");
        synchronized (this) {
            int index = Integer.parseInt(name.split("-")[1]);
            int[] row = this.getRow(index);
            Arrays.sort(row);
            this.printData(index);
            rowCount++;
            if (rowCount == 6) {
                System.out.println("row wise sorting is done now performing heapsort on all the rows");
                this.notifyAll();
            }

        }

    }

    public void printData(int index) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " is printing values of row : " + index);
        int[] i_row = this.getRow(index);
        for (int i = 0; i < i_row.length; i++) {
            System.out.println("values : " + i_row[i]);
        }

    }

    public int[] getRow(int index) {
        // String name = Thread.currentThread().getName();
        //  System.out.println(name + " is accessing the value of index " + index);
        return this.array[index];
    }

    public void setRowAtIndex(int index, int[] row) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " is sets the value at index " + index);
        this.array[index] = row;
    }

    public int[][] getArray() {
        return array;
    }


    public void heapSort() {
        PriorityQueue pq = new PriorityQueue(40);

        synchronized (this) {
            if (rowCount < 6) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < this.array.length; j++) {
               int[] row = this.array[j];
                for (int i :
                        row) {
                    pq.add(i);
                }

            }

            System.out.println("printing after heapsort");

            while (!pq.isEmpty()) {
                System.out.println("value : " + pq.remove());
            }
        }

    }


}
