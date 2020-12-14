#include <iostream>
#include "question2.h"
#include "Question3.h"
#include "Question5.h"
#include <iostream>
#include "swap.h"
#include "Question9.h"

using namespace std;

int main() {

//    question2();
//    Question3();
 //  Question5();
//
//
//    long arr [] = {6,0,9,6};
//    long *pt = arr;
//    pt++;
//    long *pt2 = arr + 3;
//
//    printf("%d %d", *pt, *pt2 );
//
//

int x =5;
int y =10;

 std::cout << "Before swap: value of x : " << x << std::endl;
 std::cout << "Before swap: value of y : " << y << std::endl;

 swapByReference(&x, &y);
 std::cout << "after swap: value of x : " << x << std::endl;
 std::cout << "after swap: value of y : " << y << std::endl;





 //
// swapByValue(x, y);
//
// std::cout << "value of x : " << x << std::endl;
// std::cout << "value of y : " << y << std::endl;


//    char charValue = 'a';
//    char* ptr1 = &charValue;
//    char** ptr2 = &ptr1;
//
//    printf("%c %c %c ", charValue, *ptr1 ,**ptr2);
//
    return 0;
//    int array[2][4] ={
//            { 1, 2, 3, 4 },
//            { 5, 6, 7, 8 }
//    };
//
//    int rows = sizeof(array)/sizeof(array[0]);
//    int cols = sizeof(array[0])/sizeof(array[0][0]);
//    int reverseArray[rows][cols];
//
//    printf("rows: %d , columns : %d ", rows,cols);
//    printf("\n");
//    for(int i = rows-1; i >= 0; i--) {
//        for(int j = cols-1; j >= 0; j--) {
//            reverseArray[rows-1-i][cols-1-j] = array[i][j];
//        }
//    }
//
//    for(int i = 0; i < rows; i++) {
//        for(int j = 0; j < cols; j++) {
//            printf("%d", reverseArray[i][j]);
//            if(j < cols-1)
//                printf(", ");
//        }
//        printf("\n");
//    }






//    std::cout << "Hello, World!" << std::endl;
//    int x=20;
//    int y=25;
//    int *charptr = &x;
//    charptr= &y;

// x == *&x
/**
 basically when we access the value of any variable  two steps performed
 1.	 Look up the address that the variable name corresponds to
 2.	 Go to that location in memory and retrieve or set the value it contains
 */
    // address of x
//    std::cout << *&x << std::endl;
//
//
//    // changing the value of x
//    std::cout << x << std::endl;
//
//    // This will give the address of x
//    std::cout << charptr << std::endl;
//
//    // this dereference the pointer and return the value of x
//    std::cout << *charptr << std::endl;


// Declare char pointer
//    char c = 'd';
//    char* cpoint =&c;
//    *cpoint ='a';
//
//    std::cout << cpoint << std::endl; // this will print the address of char
//    std::cout << c << std::endl; // this will print the value c

//// declare array of 10 int
//
//int array[10] = {1,2,3,2,4,5,6,7,8,9};
//    int array2[10] = {1,2,3,2,4,5,6,7,8,9};
//
//    int (&refrenceToArr)[10] =array;
////    int *ptr =array;
////    ptr=array2;
//
//
//    std::cout << refrenceToArr << std::endl; // this will print the value c
//
//
//
//    char str[3] = {'a','b','c'};
//    char *ptr =str;
//    std::cout << ptr[2] << std::endl; // this will print the value c
//
//    const int CINT= 10 ;
//    //CINT=20; // not allowed
//
//    char p = 'q';
//    char *ppt= &p;
//   // char *ptrp= ppt;
//
//    std::cout << &p << std::endl; // this will print the value c
//    // std::cout << ppt << std::endl; // this will print the value c
//    //std::cout << ptrp << std::endl; // this will print the value c



//    long array[] = {1,2,3,4};
//    long *ptr=array;
//    for(int i=0;i < 4;i++){
//        std::cout << ptr++ << std::endl;
//        std::cout << *ptr << std::endl;
//    }


  //  return 0;
}
