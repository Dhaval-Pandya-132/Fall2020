//
// Created by Dhaval Pandya on 11/19/20.
//
#include <iostream>
#include "Question3.h"

void Question3(){

std::cout << "Question 3 output " << std::endl;

    int* ptr1;
    int x =20;
    ptr1= &x;
    printf("printf C %d %x %x %d", x, &x, ptr1, *ptr1);
    printf("\n");

    int* ptr2;
    ptr2 = ptr1;
    printf("printf f : %d %x %x %d %x %d", x, &x, ptr1, *ptr1, ptr2, *ptr2);
    printf("\n");
    *ptr1=8;
    printf("printf h : %d %x %x %d %x %d",x, &x, ptr1, *ptr1, ptr2, *ptr2);
    printf("\n");
    *ptr2=15;
    printf("printf k : %d %x %x %d %x %d",x, &x, ptr1, *ptr1, ptr2, *ptr2);
    printf("\n");

    int arr[] = {4, 12, 8, 6};
    ptr1 = arr;

    for(int i =0 ; i <4 ;i++){

        printf("printf p for %d : %x %d %x %d", i , ptr1, *ptr1, ptr2, *ptr2);
        printf("\n");
        ptr1++;

    }







}
