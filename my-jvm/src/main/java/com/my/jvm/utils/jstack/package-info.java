package com.my.jvm.utils.jstack;

/**
 * 主要用于分析虚拟机当前线程快照，主要用于排查死锁。
 *
 *
 * 1、jstack pid
 *  例子如下：
 *  huangyundeMacBook-Pro:~ huangyun$ jstack 44657
 * 2021-04-02 15:00:13
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (15.0.1+9-18 mixed mode, sharing):
 *
 * Threads class SMR info:
 * _java_thread_list=0x00007ffa9af006f0, length=12, elements={
 * 0x00007ffa9b000600, 0x00007ffa9b04a400, 0x00007ffa9b01dc00, 0x00007ffa9b04c400,
 * 0x00007ffa9b04aa00, 0x00007ffa9b04b000, 0x00007ffa9c011600, 0x00007ffa9c009200,
 * 0x00007ffa9c83a400, 0x00007ffa9c009800, 0x00007ffa9b04f600, 0x00007ffa9c032000
 * }
 *
 * "main" #1 prio=5 os_prio=31 cpu=2375037.43ms elapsed=2552.03s tid=0x00007ffa9b000600 nid=0x2203 runnable  [0x00000001028b9000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at com.my.jvm.utils.jstat.JstatTest.main(JstatTest.java:9)
 * 	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(java.base@15.0.1/Native Method)
 * 	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(java.base@15.0.1/NativeMethodAccessorImpl.java:64)
 * 	at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(java.base@15.0.1/DelegatingMethodAccessorImpl.java:43)
 * 	at java.lang.reflect.Method.invoke(java.base@15.0.1/Method.java:564)
 * 	at com.intellij.rt.execution.application.AppMainV2.main(AppMainV2.java:131)
 *
 * "Reference Handler" #2 daemon prio=10 os_prio=31 cpu=1.20ms elapsed=2551.97s tid=0x00007ffa9b04a400 nid=0x4403 waiting on condition  [0x000070000cf0b000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at java.lang.ref.Reference.waitForReferencePendingList(java.base@15.0.1/Native Method)
 * 	at java.lang.ref.Reference.processPendingReferences(java.base@15.0.1/Reference.java:241)
 * 	at java.lang.ref.Reference$ReferenceHandler.run(java.base@15.0.1/Reference.java:213)
 *
 * "Finalizer" #3 daemon prio=8 os_prio=31 cpu=0.21ms elapsed=2551.97s tid=0x00007ffa9b01dc00 nid=0x4303 in Object.wait()  [0x000070000d00e000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 * 	at java.lang.Object.wait(java.base@15.0.1/Native Method)
 * 	- waiting on <0x00000007800021a0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at java.lang.ref.ReferenceQueue.remove(java.base@15.0.1/ReferenceQueue.java:155)
 * 	- locked <0x00000007800021a0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at java.lang.ref.ReferenceQueue.remove(java.base@15.0.1/ReferenceQueue.java:176)
 * 	at java.lang.ref.Finalizer$FinalizerThread.run(java.base@15.0.1/Finalizer.java:170)
 *
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=31 cpu=0.80ms elapsed=2551.95s tid=0x00007ffa9b04c400 nid=0x5703 runnable  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Service Thread" #5 daemon prio=9 os_prio=31 cpu=29.82ms elapsed=2551.95s tid=0x00007ffa9b04aa00 nid=0x9e03 runnable  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread0" #6 daemon prio=9 os_prio=31 cpu=28.29ms elapsed=2551.95s tid=0x00007ffa9b04b000 nid=0x5b03 waiting on condition  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *    No compile task
 *
 * "C1 CompilerThread0" #8 daemon prio=9 os_prio=31 cpu=31.30ms elapsed=2551.95s tid=0x00007ffa9c011600 nid=0x9c03 waiting on condition  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *    No compile task
 *
 * "Sweeper thread" #9 daemon prio=9 os_prio=31 cpu=0.04ms elapsed=2551.95s tid=0x00007ffa9c009200 nid=0x6003 runnable  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Notification Thread" #10 daemon prio=9 os_prio=31 cpu=0.10ms elapsed=2551.93s tid=0x00007ffa9c83a400 nid=0x9703 runnable  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Common-Cleaner" #11 daemon prio=8 os_prio=31 cpu=9.37ms elapsed=2551.91s tid=0x00007ffa9c009800 nid=0x9403 in Object.wait()  [0x000070000d8a9000]
 *    java.lang.Thread.State: TIMED_WAITING (on object monitor)
 * 	at java.lang.Object.wait(java.base@15.0.1/Native Method)
 * 	- waiting on <0x00000007800390a0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at java.lang.ref.ReferenceQueue.remove(java.base@15.0.1/ReferenceQueue.java:155)
 * 	- locked <0x00000007800390a0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at jdk.internal.ref.CleanerImpl.run(java.base@15.0.1/CleanerImpl.java:148)
 * 	at java.lang.Thread.run(java.base@15.0.1/Thread.java:832)
 * 	at jdk.internal.misc.InnocuousThread.run(java.base@15.0.1/InnocuousThread.java:134)
 *
 * "Monitor Ctrl-Break" #12 daemon prio=5 os_prio=31 cpu=42.17ms elapsed=2551.87s tid=0x00007ffa9b04f600 nid=0x6503 runnable  [0x000070000d9ac000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at sun.nio.ch.SocketDispatcher.read0(java.base@15.0.1/Native Method)
 * 	at sun.nio.ch.SocketDispatcher.read(java.base@15.0.1/SocketDispatcher.java:47)
 * 	at sun.nio.ch.NioSocketImpl.tryRead(java.base@15.0.1/NioSocketImpl.java:261)
 * 	at sun.nio.ch.NioSocketImpl.implRead(java.base@15.0.1/NioSocketImpl.java:312)
 * 	at sun.nio.ch.NioSocketImpl.read(java.base@15.0.1/NioSocketImpl.java:350)
 * 	at sun.nio.ch.NioSocketImpl$1.read(java.base@15.0.1/NioSocketImpl.java:803)
 * 	at java.net.Socket$SocketInputStream.read(java.base@15.0.1/Socket.java:981)
 * 	at sun.nio.cs.StreamDecoder.readBytes(java.base@15.0.1/StreamDecoder.java:297)
 * 	at sun.nio.cs.StreamDecoder.implRead(java.base@15.0.1/StreamDecoder.java:339)
 * 	at sun.nio.cs.StreamDecoder.read(java.base@15.0.1/StreamDecoder.java:188)
 * 	- locked <0x000000078006c398> (a java.io.InputStreamReader)
 * 	at java.io.InputStreamReader.read(java.base@15.0.1/InputStreamReader.java:181)
 * 	at java.io.BufferedReader.fill(java.base@15.0.1/BufferedReader.java:161)
 * 	at java.io.BufferedReader.readLine(java.base@15.0.1/BufferedReader.java:326)
 * 	- locked <0x000000078006c398> (a java.io.InputStreamReader)
 * 	at java.io.BufferedReader.readLine(java.base@15.0.1/BufferedReader.java:392)
 * 	at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)
 *
 * "Attach Listener" #13 daemon prio=9 os_prio=31 cpu=6.79ms elapsed=2389.06s tid=0x00007ffa9c032000 nid=0xe07 waiting on condition  [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "VM Thread" os_prio=31 cpu=61.69ms elapsed=2551.99s tid=0x00007ffa9af190e0 nid=0x4703 runnable
 *
 * "GC Thread#0" os_prio=31 cpu=14.48ms elapsed=2552.03s tid=0x00007ffa9ac09340 nid=0x3403 runnable
 *
 * "GC Thread#1" os_prio=31 cpu=10.31ms elapsed=879.53s tid=0x00007ffa9af2c2c0 nid=0x9003 runnable
 *
 * "GC Thread#2" os_prio=31 cpu=11.52ms elapsed=879.53s tid=0x00007ffa9af2cdf0 nid=0x8f03 runnable
 *
 * "G1 Main Marker" os_prio=31 cpu=0.05ms elapsed=2552.03s tid=0x00007ffa9ae054b0 nid=0x4e03 runnable
 *
 * "G1 Conc#0" os_prio=31 cpu=0.04ms elapsed=2552.03s tid=0x00007ffa9ad18a80 nid=0x3703 runnable
 *
 * "G1 Refine#0" os_prio=31 cpu=0.05ms elapsed=2552.02s tid=0x00007ffa9af15d90 nid=0x4903 runnable
 *
 * "G1 Young RemSet Sampling" os_prio=31 cpu=150.87ms elapsed=2552.02s tid=0x00007ffa9ad220b0 nid=0x3903 runnable
 *
 * "VM Periodic Task Thread" os_prio=31 cpu=718.07ms elapsed=2551.93s tid=0x00007ffa9ad33560 nid=0x6203 waiting on condition
 *
 * JNI global refs: 13, weak refs: 0
 *
 * 一个死锁排查的例子
 *
 * 前面的基本不用管，到最后，日志会打印java级别的死锁记录。相关栈信息也会记录。分析信息我们可以发现
 * pool-1-thread-2  waiting to lock <0x0000000795704618> (a java.lang.Object)
 *                  locked <0x0000000795704628> (a java.lang.Object)
 * 这个线程在等待0x0000000795704618锁的释放，并锁住了0x0000000795704628
 *
 * pool-1-thread-1  waiting to lock <0x0000000795704628> (a java.lang.Object)
 *                  locked <0x0000000795704618> (a java.lang.Object)
 * 这个线程在等待0x0000000795704628锁的释放，并锁住了0x0000000795704618
 * 这样就明显发现死锁了
 *
 *
 *
 * huangyundeMacBook-Pro:~ huangyun$ jstack 46190
 * 2021-04-02 15:09:34
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.161-b12 mixed mode):
 *
 * "Attach Listener" #13 daemon prio=9 os_prio=31 tid=0x00007fcf4488b800 nid=0x1007 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "DestroyJavaVM" #12 prio=5 os_prio=31 tid=0x00007fcf44070000 nid=0x1703 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "pool-1-thread-2" #11 prio=5 os_prio=31 tid=0x00007fcf43884000 nid=0x3c03 waiting for monitor entry [0x000070000acf6000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.t2(DeadLockDemo.java:54)
 * 	- waiting to lock <0x0000000795704618> (a java.lang.Object)
 * 	- locked <0x0000000795704628> (a java.lang.Object)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.access$100(DeadLockDemo.java:6)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo$2.run(DeadLockDemo.java:31)
 * 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * "pool-1-thread-1" #10 prio=5 os_prio=31 tid=0x00007fcf43076800 nid=0x3a03 waiting for monitor entry [0x000070000abf3000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.t1(DeadLockDemo.java:44)
 * 	- waiting to lock <0x0000000795704628> (a java.lang.Object)
 * 	- locked <0x0000000795704618> (a java.lang.Object)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.access$000(DeadLockDemo.java:6)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo$1.run(DeadLockDemo.java:21)
 * 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * "Service Thread" #9 daemon prio=9 os_prio=31 tid=0x00007fcf43069000 nid=0x4303 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C1 CompilerThread2" #8 daemon prio=9 os_prio=31 tid=0x00007fcf43056800 nid=0x3803 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread1" #7 daemon prio=9 os_prio=31 tid=0x00007fcf43055800 nid=0x3703 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread0" #6 daemon prio=9 os_prio=31 tid=0x00007fcf43883800 nid=0x4703 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Monitor Ctrl-Break" #5 daemon prio=5 os_prio=31 tid=0x00007fcf4481d800 nid=0x4803 runnable [0x000070000a5e1000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at java.net.SocketInputStream.socketRead0(Native Method)
 * 	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
 * 	at java.net.SocketInputStream.read(SocketInputStream.java:171)
 * 	at java.net.SocketInputStream.read(SocketInputStream.java:141)
 * 	at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
 * 	at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
 * 	at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
 * 	- locked <0x00000007957aefd8> (a java.io.InputStreamReader)
 * 	at java.io.InputStreamReader.read(InputStreamReader.java:184)
 * 	at java.io.BufferedReader.fill(BufferedReader.java:161)
 * 	at java.io.BufferedReader.readLine(BufferedReader.java:324)
 * 	- locked <0x00000007957aefd8> (a java.io.InputStreamReader)
 * 	at java.io.BufferedReader.readLine(BufferedReader.java:389)
 * 	at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)
 *
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007fcf4400c800 nid=0x3407 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007fcf43854800 nid=0x2c03 in Object.wait() [0x000070000a3db000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 * 	at java.lang.Object.wait(Native Method)
 * 	- waiting on <0x0000000795588ec0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 * 	- locked <0x0000000795588ec0> (a java.lang.ref.ReferenceQueue$Lock)
 * 	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 * 	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)
 *
 * "Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007fcf43001800 nid=0x4f03 in Object.wait() [0x000070000a2d8000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 * 	at java.lang.Object.wait(Native Method)
 * 	- waiting on <0x0000000795586b68> (a java.lang.ref.Reference$Lock)
 * 	at java.lang.Object.wait(Object.java:502)
 * 	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
 * 	- locked <0x0000000795586b68> (a java.lang.ref.Reference$Lock)
 * 	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)
 *
 * "VM Thread" os_prio=31 tid=0x00007fcf44807000 nid=0x2b03 runnable
 *
 * "GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007fcf43824000 nid=0x1d07 runnable
 *
 * "GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007fcf43824800 nid=0x1c03 runnable
 *
 * "GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007fcf43825000 nid=0x5403 runnable
 *
 * "GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007fcf43826000 nid=0x5203 runnable
 *
 * "VM Periodic Task Thread" os_prio=31 tid=0x00007fcf43014800 nid=0x4203 waiting on condition
 *
 * JNI global references: 33
 *
 *
 * Found one Java-level deadlock:
 * =============================
 * "pool-1-thread-2":
 *   waiting to lock monitor 0x00007fcf438542a8 (object 0x0000000795704618, a java.lang.Object),
 *   which is held by "pool-1-thread-1"
 * "pool-1-thread-1":
 *   waiting to lock monitor 0x00007fcf43851a18 (object 0x0000000795704628, a java.lang.Object),
 *   which is held by "pool-1-thread-2"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "pool-1-thread-2":
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.t2(DeadLockDemo.java:54)
 * 	- waiting to lock <0x0000000795704618> (a java.lang.Object)
 * 	- locked <0x0000000795704628> (a java.lang.Object)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.access$100(DeadLockDemo.java:6)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo$2.run(DeadLockDemo.java:31)
 * 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 * "pool-1-thread-1":
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.t1(DeadLockDemo.java:44)
 * 	- waiting to lock <0x0000000795704628> (a java.lang.Object)
 * 	- locked <0x0000000795704618> (a java.lang.Object)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo.access$000(DeadLockDemo.java:6)
 * 	at com.my.jvm.utils.jstack.DeadLockDemo$1.run(DeadLockDemo.java:21)
 * 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
 * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * Found 1 deadlock.
 */