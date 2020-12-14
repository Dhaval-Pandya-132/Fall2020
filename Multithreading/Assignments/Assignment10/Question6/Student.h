//
// Created by Dhaval Pandya on 12/5/20.
//

#ifndef QUESTION6_STUDENT_H
#define QUESTION6_STUDENT_H
#include <string>
using namespace std;

class Student {

public:
    Student(Student *pStudent);

    static int nextId;
//    static bool isGradeSubmitted;
    int id;
    char* name;
    int homework;
    int midterm;
    int finalexam;


    Student();
    static void*  writeToFile(void* object);
    void* print();

    virtual ~Student();

};


#endif //QUESTION6_STUDENT_H
