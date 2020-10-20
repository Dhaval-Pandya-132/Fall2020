package com.multithreding.Question4;

import java.util.Arrays;
import java.util.Collections;

public class main {

    public static void main(String args[]) {
        int[][] arr = {{9, 12, 6, 14, 10, 21, 13}, {3, 5, 41, 16, 14, 10, 21},
                {3, 15, 41, 17, 11, 10, 51}, {3, 15, 41, 17, 11, 10, 51},
                {4, 15, 35, 17, 11, 12, 55}, {2, 16, 31, 18, 12, 11, 42}};


        ArrayData ad = new ArrayData(arr);


        for (int k = 0; k < arr.length; k++) {
            Thread thread= new Thread(ad,"Thread-"+k);
            thread.start();
        }

        ad.heapSort();

    }

}
