package com.just.sun.jni;

/**
 * Created by walkingMen on 2016/5/20.
 */
public class NdkJniUtils {
    public native String getCLanguageString();
    static {
        System.loadLibrary("SunJniHelloLib");   //defaultConfig.ndk.moduleName
    }
}


