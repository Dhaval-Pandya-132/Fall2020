//
// Created by Dhaval Pandya on 11/21/20.
//
#include <jni.h>
#include <stdio.h>

JNIEXPORT jint JNICALL Java_com_multithreding_Question10_factorial_fact
(JNIEnv *env, jobject jobj,jint num)
{
jint result=1;

while(num)
{
result*=num;
num--;
}

return result;
}