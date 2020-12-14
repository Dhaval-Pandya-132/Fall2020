//
// Created by Dhaval Pandya on 11/19/20.
//

#include "Question5.h"
#include <iostream>
using namespace std;



void Question5(){

    cout << "inside question 5  " << endl;
    float arr[3];
    // declare pointer variable
    float *ptr;
    cout << "Displaying address using arrays: " << endl;
    // use for loop to print addresses of all array elements
    for (int i = 0; i < 3; ++i) {
        cout << "&arr[" << i << "] = " << &arr[i] << endl;
    }
    // ptr = &arr[0]
    ptr = arr;
    cout<<"\nDisplaying address using pointers: "<< endl;
    // use for loop to print addresses of all array elements
    // using pointer notation
    for (int i = 0; i < 3; ++i)  {
        cout << "ptr + " << i << " = "<< ptr + i << endl;
    }


}