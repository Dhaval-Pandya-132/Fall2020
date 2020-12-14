package com.multithreding;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Integer i1 = new Integer(5);

        Integer i2 = new Integer(10);

        System.out.println("Before Swap : value of i1 and i2 "+ i1 +" , "+i2 );

        swapIntegers(i1,i2);

        System.out.println("after Swap : value of i1 and i2 "+ i1 +" , "+i2 );


        int[][] array = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 }
        };
        int rows = array.length;
        int cols = array[0].length;
        int[][] reverse = new int[rows][cols];
        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >= 0; j--) {
                reverse[rows-1-i][cols-1-j] = array[i][j];
            }
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
               // System.out.print(reverse[i][j]);
                if(j < cols-1){}
                   // System.out.print(", ");
            }
           // System.out.println();
        }
    }


    public static void swapIntegers(Integer i1 , Integer i2){
        Integer temp = i1;
        i1=i2;
        i2=temp;
    }



}

