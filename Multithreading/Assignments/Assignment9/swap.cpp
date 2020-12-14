//
// Created by Dhaval Pandya on 11/19/20.
//

#include "swap.h"


void swapByReference(int *x ,int *y ){
    int temp =  *x;
    *x=*y;
    *y=  temp;
}

void swapByValue(int x , int y){
    int temp =  x;
    x=y;
    y=temp;
}