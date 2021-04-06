package com.my.jvm.utils.jstack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockDemo {

    private static final Object O1 = new Object();
    private static final Object O2 = new Object();

    /**
     * 模拟一个死锁功能,可以用jstack排查
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    t1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    t2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void t1() throws InterruptedException {
        synchronized (O1) {
            System.out.println("hello");
            Thread.sleep(10000);
            synchronized (O2) {
                System.out.println("t1");
            }
        }
    }

    private static void t2() throws InterruptedException {
        synchronized (O2) {
            System.out.println("wawawa");
            Thread.sleep(10000);
            synchronized (O1) {
                System.out.println("t2");
            }
        }
    }
}
