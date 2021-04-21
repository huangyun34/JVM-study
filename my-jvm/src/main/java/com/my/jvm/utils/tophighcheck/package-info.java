package com.my.jvm.utils.tophighcheck;

/**
 * cpu过高排查思路
 *
 * 先通过top命令，查看cpu过高的进程
 * 例子如下：
 *   PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
 * 21134 root      20   0 4712672 897632   8016 S   3.7  2.8 266:53.74 java
 *  8911 root      10 -10  168888  41548   5900 S   3.3  0.1 150:56.02 AliYunDun
 * 24519 elastic   20   0   10.9g   7.5g  20920 S   1.3 24.5  54:36.33 java
 *  6750 root      20   0 4538024   1.0g  14276 S   1.0  3.2  79:49.00 java
 * 26026 root      20   0 4392296 867812  15072 S   1.0  2.7  26:38.58 java
 * 21180 root      20   0 4376484 739896   7132 S   0.7  2.3  56:56.75 java
 * 然后通过top命令查看该进程，并输入H查看该进程下的线程：
 * 如下：
 * top -p 21134
 * 回车后再输入H
 * PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
 * 21455 root      20   0 4712672 897632   8016 S  0.3  2.8  10:56.82 java
 * 21617 root      20   0 4712672 897632   8016 S  0.3  2.8  10:59.56 java
 * 21674 root      20   0 4712672 897632   8016 S  0.3  2.8   0:23.89 java
 * 21705 root      20   0 4712672 897632   8016 S  0.3  2.8   0:11.54 java
 * 21724 root      20   0 4712672 897632   8016 S  0.3  2.8  10:59.44 java
 * 21738 root      20   0 4712672 897632   8016 S  0.3  2.8  10:55.50 java
 * 21134 root      20   0 4712672 897632   8016 S  0.0  2.8   0:00.00 java
 * 21140 root      20   0 4712672 897632   8016 S  0.0  2.8   0:35.10 java
 * 21141 root      20   0 4712672 897632   8016 S  0.0  2.8   1:10.98 java
 * 21142 root      20   0 4712672 897632   8016 S  0.0  2.8   1:11.14 java
 * 21143 root      20   0 4712672 897632   8016 S  0.0  2.8   1:10.92 java
 * 21144 root      20   0 4712672 897632   8016 S  0.0  2.8   1:10.96 java
 * 21145 root      20   0 4712672 897632   8016 S  0.0  2.8   1:31.95 java
 * 21146 root      20   0 4712672 897632   8016 S  0.0  2.8   0:00.22 java
 * 21147 root      20   0 4712672 897632   8016 S  0.0  2.8   0:00.18 java
 *
 * 找出cpu比较高的线程，记录pid转换成
 *
 * 再使用jstack查看栈信息
 * 例子如下：
 * jstack 21134
 *
 * "NettyClientWorkerThread_4" #163 prio=5 os_prio=0 tid=0x00007f22e800e800 nid=0x548a waiting on condition [0x00007f22aa1e0000]
 *    java.lang.Thread.State: TIMED_WAITING (parking)
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x00000000e31237d8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
 *         at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
 *         at java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.SingleThreadEventExecutor.takeTask(SingleThreadEventExecutor.java:269)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.DefaultEventExecutor.run(DefaultEventExecutor.java:39)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:140)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * "NettyClientWorkerThread_3" #162 prio=5 os_prio=0 tid=0x00007f22e800c800 nid=0x5489 waiting on condition [0x00007f22aa2e1000]
 *    java.lang.Thread.State: TIMED_WAITING (parking)
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x00000000e3127f98> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
 *         at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
 *         at java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.SingleThreadEventExecutor.takeTask(SingleThreadEventExecutor.java:269)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.DefaultEventExecutor.run(DefaultEventExecutor.java:39)
 *         at com.aliyun.openservices.shade.io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:140)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * 查找对应cpu高的线程id，（事先把之前的二进制线程id变成16进制，然后拿这个值去记录里找nid相等的，就找到了对应线程栈信息）
 *
 * 基本就定义出来了问题
 *
 * 如果问题是内存泄漏可以用这个看哪些对象过多，来分析
 * jmap -histo 21134|head -20
 */