#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_multithreding_Question10_HelloWorld_cfunction
(JNIEnv *env, jobject jobj)
{
printf("\n > C says HelloWorld !\n");
}