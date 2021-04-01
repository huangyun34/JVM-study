package com.my.jvm.classloader;

public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;
    public static final String HELLO = "hello";
    public static final int WHAT = value;
}
