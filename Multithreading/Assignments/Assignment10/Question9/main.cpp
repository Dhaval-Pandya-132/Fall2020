#include<stdio.h>
#include<stdlib.h>
#include <iostream>

class IntegerArray {
public:
    int *data;
    int size;

    IntegerArray(IntegerArray &o) {
        data = new int[o.size];
        size = o.size;
        for (int i = 0; i < size; ++i)
            data[i] = o.data[i];
    }
    IntegerArray(int size) {
        data = new int[size];
        this->size = size;
    }
    ~IntegerArray() {
        delete[] data;
    }
};


int main() {
    IntegerArray a(2);
    a.data[0] = 4;
    a.data[1] = 2;
    IntegerArray b = a;

    std::cout << a.data[0] << std::endl; // not 4!

}
