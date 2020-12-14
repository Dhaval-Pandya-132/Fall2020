//
// Created by Dhaval Pandya on 11/21/20.
//
#include <jni.h>
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_multithreding_Question10_reverseString_reversefunc
        (JNIEnv *env,jobject jobj,jstring original)
{
    const char *org;
    char rev[100];
    printf("test 1");
    org = (*env)->GetStringUTFChars(env,original,NULL);

    int i;
    printf("test 2");
    int size = (*env)->GetStringUTFLength(env,original);
    printf("test 3 : %d :" ,size);

    for(i=0;i<size;i++)
        rev[i]=org[size-i-1];

    rev[size]='\0';
    printf("test 4");
    printf("printing reverse string %s" , rev);
    printf("test 5");
    return (*env)->NewStringUTF(env,rev);
}