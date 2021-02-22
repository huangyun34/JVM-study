package com.my.jvm.string;

import com.my.jvm.bo.Person;

/**
 * 创建string的方式，大致分为6种，如下
 */
public class StringStudy {

    public void t1() {
        /**
         * 这种方法创建步骤
         * 1、编译时，则会在class常量池和字符串常量池中创建abc常量
         * 2、运行时，检查abc是否在字符串常量池里已被创建，如果被创建，则直接使用其引用，如未被创建，则要在字符串常量池里创建abc
         */
        String s = "abc";
    }

    public void t2() {
        /**
         * 这种方法创建步骤
         * 1、编译时，则会在class常量池和字符串常量池中创建abc常量
         * 2、运行时，在调用new时，会在堆中创建字符串对象，并引用字符串常量池中的字符串对象char[]数组，并返回对象引用，s只是字符串对象的引用
         * 也就是说s只是一个引用指向字符串对象，字符串对象引用字符串常量池中的char[]数组
         */
        String s = new String("abc");
    }

    public void t3() {
        /**
         * 这种方法步骤
         * 1、在运行时，创建的string对象会在堆中直接创建，而不会再常量池中创建，这是代表从其他数据传递过来的值，没有明面的字符串
         * 2、如果想nihao这样的，他还是会被编译器优化，也会在字符串常量池中创建
         */
        Person person = new Person();
        person.setName("nihao");
        person.setName("来自数据库传值");
    }

    public void t4() {
        /**
         * 这种方法步骤
         * 如果按照理论来说，因为字符串无法拼接所以，会生成3个对象分别是abc,abcd,abcdef,明显这样是效率最低，
         * 所以主流编译器会对此方式做优化，直接优化成String s = "abcdef";
         * 1、编译器优化成第一种方式
         */
        String s = "abc" + "d" + "ef";
    }

    public void t5() {
        /**
         * 这种方法步骤
         * 和上面一种原理上是一样的，编译器也会对他做优化，优化成下面这种方式
         * 1、编译器优化
         */
//        String s = "abc";
//        for (int i = 0; i < 100; i++) {
//            s = s + i;
//        }

        //优化后的代码
        String s = "abc";
        for (int i = 0; i < 100; i++) {
            s = (new StringBuilder(String.valueOf(s)).append(i).toString());
        }
    }

    public void t6() {
        /**
         * 此方法步骤
         * 因为此方法调用了intern，他会在在字符串常量池中检查是否有abc，如果有直接返回引用，没有就创建再返回引用（引用只能在栈或者方法区中），
         * 这样就可以减少堆中对象的创建
         */
        String s = new String("abc").intern();
        String s1 = new String("abc").intern();
    }
}
