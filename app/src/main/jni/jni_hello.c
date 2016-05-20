//
// Created by walkingMen on 2016/5/20.
//
#include "com_just_sun_jni_NdkJniUtils.h"
/*
 * Class:     com_just_sun_jni_NdkJniUtils
 * Method:    getCLanguageString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_just_sun_jni_NdkJniUtils_getCLanguageString
  (JNIEnv *env, jobject obj){
     return (*env)->NewStringUTF(env,"This just a test for Android Studio NDK JNI developer!");
  }
