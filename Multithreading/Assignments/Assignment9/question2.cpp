//
// Created by Dhaval Pandya on 11/18/20.
//

#include "question2.h"
#include <iostream>

void question2(){

    //std::cout << "inside question2 Hello, World!" << std::endl;

    int x=4;
    int *ptr1=&x;
    printf("%d %x %x %d", x, &x, ptr1, *ptr1);
//    std::cout << ptr1 << std::endl;
    printf("\n");

}