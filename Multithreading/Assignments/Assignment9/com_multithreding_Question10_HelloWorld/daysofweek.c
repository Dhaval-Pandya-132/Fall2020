//
// Created by Dhaval Pandya on 11/21/20.
//
#include <jni.h>
#include <stdio.h>

JNIEXPORT jobjectArray JNICALL Java_com_multithreding_Question10_daysofweek_returndays
        (JNIEnv *env, jobject jobj) {

    char *days[] = {"Sunday",
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday"};

    jstring str;
    jobjectArray day = 0;
    jsize len = 7;
    int i;

    day = (*env)->NewObjectArray(env, len, (*env)->FindClass(env, "java/lang/String"), 0);

    for (i = 0; i < 7; i++) {
        str = (*env)->NewStringUTF(env, days[i]);
        (*env)->SetObjectArrayElement(env, day, i, str);
    }

    return day;
}