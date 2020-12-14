//
// Created by Dhaval Pandya on 11/21/20.
//
#include <jni.h>
#include <stdio.h>


JNIEXPORT jstring JNICALL Java_com_multithreding_Question10_reverse_reversefunc
        (JNIEnv *env, jobject jobj, jstring original){
    const char *org;
    char *rev;

    printf("running from c :");

    org = (*env)->GetStringUTFChars(env,original,NULL);

    int i;
    int size = (*env)->GetStringUTFLength(env,original);

    for(i=0;i<size;i++){
        rev[i]=org[size-i-1];
    }

    rev[size]='\0';
//(*env)->NewStringUTF(env,rev)
    return (*env)->NewStringUTF(env,rev);
}