package com.my.jvm.generic;

import java.util.List;

/**
 * 泛型注意事项
 * 泛型参数在idea等编译器是无法通过编译的（因为idea等编译器会做泛型擦除，泛型擦除以后，就会导致参数摸一摸样的，所有会报错）
 * 但是jdk的编译器是可以通过(方法的特征：返回类型+方法名+param)
 * @author huangyun
 */
public class GenericConflict<U, V> {

//    public String t1(List<String> l1) {
//        return "";
//    }

    public Integer t1(List<Integer> l2) {
        return null;
    }

//    public void t2(U u) {
//
//    }

    public void t2(V v) {

    }

    public void t3(String a) {

    }

    public void t3(Integer a) {

    }

}
