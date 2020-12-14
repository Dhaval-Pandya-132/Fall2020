//
// Created by Dhaval Pandya on 12/5/20.
//

#include "Student.h"
#include <iostream>
#include <fstream>
pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;
Student::Student() {
    nextId++;
//    isGradeSubmitted=false;
}

Student::~Student() {

}

void* Student::writeToFile(void* object) {
    pthread_mutex_lock(&mutex1);
    Student* cptr = (Student*)object;
    printf("\n");
    printf("{id:%d,nextId:%d, name: %s,midterm:%d,finalexam:%d,homework:%d }"
           ,cptr->id
           ,Student::nextId
           ,cptr->name
           ,cptr->midterm
           ,cptr->finalexam
           ,cptr->homework
           );

//    std::ofstream o("grade.txt");
    std::ofstream outfile;
    outfile.open("grade.txt", std::ios_base::app);

    outfile << cptr->id << "," << Student::nextId
    << "," << cptr->name
    << "," << cptr->homework
    << "," << cptr->midterm
    << "," << cptr->finalexam;
    outfile << "\n";
    outfile.close();
    pthread_mutex_unlock(&mutex1);
    return NULL;
}

void* Student::print(){
    cout << this->id;
    return NULL;
}

Student::Student(Student *pStudent) {

}
