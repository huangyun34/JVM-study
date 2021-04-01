package com.my.jvm.classloader;

/**
 * 通过JVM参数可以观察操作是否会导致子类的加载 -XX:+TraceClassLoading
 */
public class Initalization {

    public static void main(String[] args) {
        Initalization initalization = new Initalization();
//        initalization.m1();
//        initalization.m2();
//        initalization.m3();
        initalization.m4();
    }

    /**
     * 调用子类的static变量，会使父类和子类加载，父类初始化,子类不会初始化
     * 可以通过字节码查看，
     * getstatic #19 <com/my/jvm/classloader/SubClass.value>
     * 因为getstatic会出发初始化，然后SubClass中没有value值，所以调用的使父类的属性，所以先触发父类的初始化，后触发子类的初始化
     * 由于父类子类都初始化了，肯定会加载，因为加载在初始化之前就做好了
     */
    private void m1() {
        System.out.println(SubClass.value);
    }

    /**
     * 创建父类的数组，会使父类加载，子类不加载，父类子类都不初始化
     */
    private void m2() {
        SuperClass[] s = new SuperClass[10];
    }

    /**
     * 访问父类常量
     * 父类、子类不初始化，父类、子类不加载
     */
    private void m3() {
        System.out.println(SuperClass.HELLO);
    }

    /**
     * 打印父类常量，把变量的值复制给了这个常量
     * 父类初始化，子类不初始化，父类加载，子类不加载
     * getstatic #38 <com/my/jvm/classloader/SuperClass.WHAT>
     */
    private void m4() {
        System.out.println(SuperClass.WHAT);
    }
}
