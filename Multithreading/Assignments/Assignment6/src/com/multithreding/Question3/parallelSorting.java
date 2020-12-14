package com.multithreding.Question3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class parallelSorting {

    public static void main(String args[]){
        List<Integer> al = new ArrayList<>();
        Integer[][] arr = { { 9, 12, 6, 14, 10, 21, 13}, { 3, 5, 41, 16, 14, 10, 21},
                { 3, 15, 41, 17, 11, 10, 51}, { 3, 15, 41, 17, 11, 10, 51},
                { 4, 15, 35, 17, 11, 12, 55}, { 2, 16, 31, 18, 12, 11, 42},
                { 2, 15, 35, 10, 11, 12, 19}, { 1, 20, 33, 18, 12, 13, 44} };

        for(int i =0; i< arr.length; i++){
            al.addAll(Arrays.asList(arr[i]));
        }
        al.parallelStream().sorted().collect(Collectors.toList()).forEach(System.out::println);

//        al.stream().parallel().sorted().forEach(x->{
//            System.out.println(Thread.currentThread().getName());
//        });


//        System.out.println("printing after sorting ");
//        parallelSorting.printValues(arr);




    }


    public static void  printValues(int[][] arr){
        for(int i = 0; i < arr.length ;i++){

            System.out.println("printing row : "+i );
            for (int j = 0; j < arr[i].length ; j++){
                System.out.println("values: "+ arr[i][j]);
            }

        }
    }




}
