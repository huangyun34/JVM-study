package com.my.jvm.classloader;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}
