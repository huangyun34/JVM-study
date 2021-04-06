package com.my.jvm.utils.arthas;

/**
 * 命令：
 * dashboard 仪表盘
 *
 * thread 线程相关信息
 * -n 最忙的前几个（非常有用）
 * -b 可以查看死锁的线程
 * 例子如下：
 * [arthas@47403]$ thread -b
 * "pool-1-thread-2" Id=11 BLOCKED on java.lang.Object@6fd578ab owned by "pool-1-thread-1" Id=10
 *     at com.my.jvm.utils.jstack.DeadLockDemo.t2(DeadLockDemo.java:54)
 *     -  blocked on java.lang.Object@6fd578ab
 *     -  locked java.lang.Object@df3dea3 <---- but blocks 1 other threads!
 *     at com.my.jvm.utils.jstack.DeadLockDemo.access$100(DeadLockDemo.java:6)
 *     at com.my.jvm.utils.jstack.DeadLockDemo$2.run(DeadLockDemo.java:31)
 *     at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 *     at java.lang.Thread.run(Thread.java:748)
 *
 *     Number of locked synchronizers = 1
 *     - java.util.concurrent.ThreadPoolExecutor$Worker@66d3c617
 *
 * -i 显示采样
 * thread -i 1000 -n 3   意思是1000毫秒时间段内采样一次，采线程最忙的前3个线程
 * 例子如下：
 * [arthas@47403]$ thread -i 1000 -n 3
 * "arthas-command-execute" Id=42 cpuUsage=0.09% deltaTime=0ms time=35ms RUNNABLE
 *     at sun.management.ThreadImpl.dumpThreads0(Native Method)
 *     at sun.management.ThreadImpl.getThreadInfo(ThreadImpl.java:448)
 *     at com.taobao.arthas.core.command.monitor200.ThreadCommand.processTopBusyThreads(ThreadCommand.java:199)
 *     at com.taobao.arthas.core.command.monitor200.ThreadCommand.process(ThreadCommand.java:122)
 *     at com.taobao.arthas.core.shell.command.impl.AnnotatedCommandImpl.process(AnnotatedCommandImpl.java:82)
 *     at com.taobao.arthas.core.shell.command.impl.AnnotatedCommandImpl.access$100(AnnotatedCommandImpl.java:18)
 *     at com.taobao.arthas.core.shell.command.impl.AnnotatedCommandImpl$ProcessHandler.handle(AnnotatedCommandImpl.java:111)
 *     at com.taobao.arthas.core.shell.command.impl.AnnotatedCommandImpl$ProcessHandler.handle(AnnotatedCommandImpl.java:108)
 *     at com.taobao.arthas.core.shell.system.impl.ProcessImpl$CommandProcessTask.run(ProcessImpl.java:385)
 *     at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 *     at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
 *     at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
 *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 *     at java.lang.Thread.run(Thread.java:748)
 *
 *
 * "VM Periodic Task Thread" [Internal] cpuUsage=0.07% deltaTime=0ms time=818ms
 *
 *
 * "VM Thread" [Internal] cpuUsage=0.06% deltaTime=0ms time=187ms
 *
 *
 * thread -state WAITING   查看等待线程
 * 例子如下：
 * [arthas@47403]$ thread -state WAITING
 * Threads Total: 19, NEW: 0, RUNNABLE: 10, BLOCKED: 2, WAITING: 5, TIMED_WAITING: 2, TERMINATED: 0
 * ID    NAME                                   GROUP               PRIORITY     STATE       %CPU         DELTA_TIME   TIME         INTERRUPTED  DAEMON
 * 2     Reference Handler                      system              10           WAITING     0.0          0.000        0:0.003      false        true
 * 3     Finalizer                              system              8            WAITING     0.0          0.000        0:0.005      false        true
 * 17    RMI Scheduler(0)                       system              9            WAITING     0.0          0.000        0:0.007      false        true
 * 31    arthas-timer                           system              9            WAITING     0.0          0.000        0:0.000      false        true
 * 39    arthas-UserStat                        system              9            WAITING     0.0          0.000        0:0.000      false        true
 *
 *
 * jvm 可以显示jvm信息
 * 例子如下：
 * [arthas@47403]$ jvm
 *  RUNTIME
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  MACHINE-NAME                                47403@huangyundeMacBook-Pro.local
 *  JVM-START-TIME                              2021-04-02 15:47:44
 *  MANAGEMENT-SPEC-VERSION                     1.2
 *  SPEC-NAME                                   Java Virtual Machine Specification
 *  SPEC-VENDOR                                 Oracle Corporation
 *  SPEC-VERSION                                1.8
 *  VM-NAME                                     Java HotSpot(TM) 64-Bit Server VM
 *  VM-VENDOR                                   Oracle Corporation
 *  VM-VERSION                                  25.161-b12
 *  INPUT-ARGUMENTS                             -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=61885:/Applications/IntelliJ IDEA.app/Con
 *                                              tents/bin
 *                                              -Dfile.encoding=UTF-8
 *
 *  CLASS-PATH                                  /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtu
 *                                              alMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.j
 *                                              dk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jr
 *                                              e/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/jaccess.jar:
 *                                              /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirt
 *                                              ualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.
 *                                              8.0_161.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents
 *                                              /Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/sunj
 *                                              ce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/L
 *                                              ibrary/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtua
 *                                              lMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jd
 *                                              k/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/jfr.
 *                                              jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVir
 *                                              tualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.j
 *                                              dk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Hom
 *                                              e/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/resources.jar:/
 *                                              Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachi
 *                                              nes/jdk1.8.0_161.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Cont
 *                                              ents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/lib/javafx-mx.jar:/Libr
 *                                              ary/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachine
 *                                              s/jdk1.8.0_161.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents
 *                                              /Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/lib/tools.jar:/Users/hu
 *                                              angyun/IdeaProjects/JVM-study/my-jvm/target/classes:/usr/local/src/apache-maven-3.5.3/repo/org/projectlombok/
 *                                              lombok/1.18.18/lombok-1.18.18.jar:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar
 *  BOOT-CLASS-PATH                             /Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirt
 *                                              ualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/
 *                                              Contents/Home/jre/lib/sunrsasign.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib
 *                                              /jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/Java
 *                                              VirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.
 *                                              0_161.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/
 *                                              classes
 *  LIBRARY-PATH                                /Users/huangyun/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Lib
 *                                              rary/Java/Extensions:/usr/lib/java:.
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  CLASS-LOADING
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  LOADED-CLASS-COUNT                          4135
 *  TOTAL-LOADED-CLASS-COUNT                    4135
 *  UNLOADED-CLASS-COUNT                        0
 *  IS-VERBOSE                                  false
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  COMPILATION
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  NAME                                        HotSpot 64-Bit Tiered Compilers
 *  TOTAL-COMPILE-TIME                          4986
 *  [time (ms)]
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  GARBAGE-COLLECTORS
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  PS Scavenge                                 name : PS Scavenge
 *  [count/time (ms)]                           collectionCount : 4
 *                                              collectionTime : 47
 *
 *  PS MarkSweep                                name : PS MarkSweep
 *  [count/time (ms)]                           collectionCount : 1
 *                                              collectionTime : 31
 *
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  MEMORY-MANAGERS
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  CodeCacheManager                            Code Cache
 *
 *  Metaspace Manager                           Metaspace
 *                                              Compressed Class Space
 *
 *  PS Scavenge                                 PS Eden Space
 *                                              PS Survivor Space
 *
 *  PS MarkSweep                                PS Eden Space
 *                                              PS Survivor Space
 *                                              PS Old Gen
 *
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  MEMORY
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  HEAP-MEMORY-USAGE                           init : 134217728(128.0 MiB)
 *  [memory in bytes]                           used : 37704072(36.0 MiB)
 *                                              committed : 84934656(81.0 MiB)
 *                                              max : 1908932608(1.8 GiB)
 *
 *  NO-HEAP-MEMORY-USAGE                        init : 2555904(2.4 MiB)
 *  [memory in bytes]                           used : 32386648(30.9 MiB)
 *                                              committed : 36143104(34.5 MiB)
 *                                              max : -1(-1 B)
 *
 *  PENDING-FINALIZE-COUNT                      0
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  OPERATING-SYSTEM
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  OS                                          Mac OS X
 *  ARCH                                        x86_64
 *  PROCESSORS-COUNT                            4
 *  LOAD-AVERAGE                                3.08740234375
 *  VERSION                                     10.13.2
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  THREAD
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  COUNT                                       19
 *  DAEMON-COUNT                                16
 *  PEAK-COUNT                                  20
 *  STARTED-COUNT                               37
 *  DEADLOCK-COUNT                              2
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  FILE-DESCRIPTOR
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------
 *  MAX-FILE-DESCRIPTOR-COUNT                   10240
 *  OPEN-FILE-DESCRIPTOR-COUNT                  126
 *
 *
 *
 *
 *
 * jad 反编译 指定已经加载的类（内存中）
 * jad 类的完整限定名
 * 例子如下：
 * jad com.tmzh.adminapi.controller.AgreementController
 * 可以站看在在到内存中的java文件（经过反编译的）
 *
 * trace 统计方法耗时。
 * trace 类的完整限定名 方法名
 * 例子如下：
 * [arthas@48656]$ trace com.tmzh.adminapi.controller.TransferController stop
 * Press Q or Ctrl+C to abort.
 * Affect(class count: 1 , method count: 1) cost in 279 ms, listenerId: 1
 * Q是退出。如果不退出，当有请求进来时，会打印方法执行情况
 *
 *
 * monitor 和trace类似，也是去统计方法耗时，不过他是按时间间隔打印,当被执行后就开始打印
 * 例子如下：
 * [arthas@48656]$ monitor -c 5 com.tmzh.adminapi.controller.TransferController stop
 * Press Q or Ctrl+C to abort.
 * Affect(class count: 1 , method count: 1) cost in 175 ms, listenerId: 2
 *
 *
 * watch 查看方法的入参、出参
 * watch 累的完整限定名 方法名 'params[0],params[1],...,returnObj'
 * 例子如下：
 *[arthas@48656]$ watch com.tmzh.adminapi.controller.TransferController stop 'params[0],params[1],...,returnObj'
 * Press Q or Ctrl+C to abort.
 * Affect(class count: 1 , method count: 1) cost in 189 ms, listenerId: 3
 *
 * 其他的看笔记
 */