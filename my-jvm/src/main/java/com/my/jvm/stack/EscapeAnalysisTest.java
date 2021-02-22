package com.my.jvm.stack;

/**
 * 逃逸分析
 * 前情：
 * 几乎所有的对象都分配在堆中，但是有一些特殊的对象却可以分配在栈中
 * 那么什么对象可以分配到栈中呢，那么需要有些条件。
 * 结论：
 * 最重要的条件是该对象经过逃逸分析，判断他是否能逃逸，如果能逃逸那么只能分配到堆中，
 * 如果不能逃逸，则有可能分配到栈中（栈上分配），随着线程而消失，不用进行垃圾回收
 * 这是jvm的一种优化，减少GC的优化
 * 默认情况下，jvm的逃逸分析是开启的，也可以手动设置：-XX:-DoEscapeAnalysis 关闭逃逸分析，-XX:+DoEscapeAnalysis 开启逃逸分析
 * -XX:+PrintGC 打印GC日志
 */
public class EscapeAnalysisTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000000; i++) {
            allocate();
        }
        long end = System.currentTimeMillis();
        //我们打开逃逸分析时，只需要5ms，而且无gc
        //关闭逃逸分析时，却需要280ms，且有6次gc打印
        System.out.println(end - start + "ms");
    }

    static void allocate() {
        MyObject myObject = new MyObject();
    }

    private static class MyObject {

    }
}
