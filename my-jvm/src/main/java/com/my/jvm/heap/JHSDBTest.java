package com.my.jvm.heap;

import com.my.jvm.bo.Person;

/**
 * HSDB工具类，使用方式，进入对应jdk的/lib中执行 sudo java -cp ./sa-jdi.jar sun.jvm.hotspot.HSDB
 *
 * 作用可以查看此进程中所有线程的内存模型，内存物理映射
 * 想要知道是那个进程只需要使用 jps 命令，找到对应的JHSDBTest类的进程id即可
 * 工具栏中的Tools->Objects Histogram 可以查看选中的线程中的所有对象，搜索自己想要的对象，并双击可以查看对象信息，重要的是拿到对象内存地址
 * 然后通过Tools->Heap Parameters进入堆信息，查看对象内存地址在堆中的位置
 * 查看栈信息的话，就需要选定某个线程，然后点击小窗中工具栏第二个图标，show the stack memory for the current thread
 *
 */
public class JHSDBTest {

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        person.setName("小花");
        person.setAge(11);
        for (int i = 0; i < 20; i++) {
            //主动gc查看堆中对象的变化（新老年代变化）
            System.gc();
        }
        Person person1 = new Person();
        person1.setName("小懵逼");
        person1.setAge(12);
        //保证线程不结束，这样可以通过HSDB慢慢查看
        Thread.sleep(Integer.MAX_VALUE);
    }
}
