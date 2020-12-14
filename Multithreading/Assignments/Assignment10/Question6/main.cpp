#include <iostream>
#include "Student.h"
#include <cstdlib>
#include<ctime>
#include <thread>
#include <fstream>
#include <map>
#define MAX 150
#include <chrono>
using namespace std::chrono;
pthread_mutex_t mutex2 = PTHREAD_MUTEX_INITIALIZER;
using namespace std;

int Student::nextId = 0;
std::map<int, std::string> readmap;
void* graderFunction(void*);
string calculateGrade(int, int, int);

string calculateGrade(int homework , int midterm, int final){
    int grade= homework*0.5+midterm*0.3+final*0.2;
    if(grade >90 && grade <= 100)
        return "A";
    else if (grade >85 && grade <= 90)
        return "B";
    else if (grade >80 && grade <= 85)
        return "C";
    else if (grade >70 && grade <= 80)
        return "D";
    else
        return "F";

}

void* graderFunction(void*){
    pthread_mutex_lock(&mutex2);
    std::this_thread::sleep_for(3000ms);
    cout << "\n";
    cout << "printing from grader thread \n";
    string line;


    while(readmap.size()  < MAX ){
     //   std::this_thread::sleep_for(1000ms);
        ifstream myfile("grade.txt");
    if (myfile.is_open())
    {
        while ( getline (myfile,line) )
        {
            std::string s = line;
            std::string delimiter = ",";
            string arr[6]={};
            int i=0;
            size_t pos = 0;
            std::string token;
            while ((pos = s.find(delimiter)) != std::string::npos) {
                token = s.substr(0, pos);
             //   std::cout << token << std::endl;
                arr[i]=token;
                s.erase(0, pos + delimiter.length());
                i++;
            }
           // std::cout << s << std::endl;
            arr[i]=s;
            if(readmap.find(stoi(arr[0])) == readmap.end()) {
                cout << "\n" << "Grader thread calculating Grades" ;
                cout << "\n" << line ;
                std::cout << "\nGrade : " << calculateGrade(stoi(arr[3]), stoi(arr[4]), stoi(arr[5]));
            }
            readmap.insert(std::pair<int,string>(stoi(arr[0]),line));
        }
        myfile.close();
    }
    else cout << "\nUnable to open file";
    }
    pthread_mutex_unlock(&mutex2);
    return NULL;
}


int main() {
    auto start = high_resolution_clock::now();
    std::cout << "Hello, World!" << std::endl;
    std::ofstream o("grade.txt");
    o << "id" << "," << "nextId"
           << "," << "name"
           << "," << "homework"
           << "," << "midterm"
           << "," << "finalexam" << "\n";
//    Student *st = new Student(1, "dhaval", 80, 80, 80, 1);

//    std::cout << midterm << "," << finalExam << "," << homework << std::endl;
  //  std::this_thread::sleep_for(1000ms);
    int min=75;
    int max=100;
    pthread_t graderThread;
    pthread_create(&graderThread,
                   NULL, graderFunction , NULL);

    srand (time(NULL));
    pthread_t producerThread;
    for(int i=0 ; i < MAX ; i++){

        int midterm = std::rand() % (max + 1 - min) + min;
        int finalExam = std::rand() % (max + 1 - min) + min;
        int homework = std::rand() % (max + 1 - min) + min;

        Student st;
        st.midterm=midterm;
        st.id=i+1;
        std::string stuName= "dhaval-" + std::to_string(i);;
        char *stName = const_cast<char *>(stuName.c_str());
        st.name= stName  ;
        st.homework=homework;
        st.finalexam=finalExam;


        pthread_create(&producerThread,
                       NULL, &Student::writeToFile , &st);
        pthread_join(producerThread, NULL);
        std::this_thread::sleep_for(1000ms);


    }
    pthread_join(graderThread, NULL);
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);

// To get the value of duration use the count()
// member function on the duration object
    cout << "\nExecution time " << duration.count() << " ms" << endl;
    return 0;
}
