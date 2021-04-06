package com.my.jvm.utils;

/**
 *
 *  这一块主要是jvm的工具
 *
 *  vm参数分类
 *  一般分为三种
 *  1）标准选项，所有的所有虚拟机实现都支持。特点：参数前缀为 - ，例子-d32。
 *  2）非标准选项，-Xmn就是特定的hotspot版本支持，这种不同虚拟机可能会变化，但应该变化不大。特点：参数前缀为 -X，例子：-Xmn.
 *  3）高级运行时选项，这些东西可以被开发人员调整，变化很大，不同版本的虚拟机、不通型号的虚拟机都差异很大。特点：参数前缀为 -XX，例子：-XX:+CheckEndorsedAndExtDirs.
 *
 * 一、命令行工具
 *
 *  1、jps 虚拟机进程状况工具
 * {@link com.my.jvm.utils.jps}
 *
 * 2、jstat 虚拟机统计信息监视工具
 * {@link com.my.jvm.utils.jstat}
 *
 * 3、jinfo java配置信息工具
 * {@link com.my.jvm.utils.jinfo}
 *
 * 4、jmap java内存映射工具
 * {@link com.my.jvm.utils.jmap}
 *
 * 5、jhat 虚拟机堆转储快照分析工具
 * {@link com.my.jvm.utils.jhat}
 *
 * 6、jstack Java堆栈跟踪工具
 * {@link com.my.jvm.utils.jstack}
 *
 * 总结：
 * one：命令行工具是生产环境使用最多的排查工具
 *
 * two：生产服务器推荐开启
 * -XX:-HeapDumpOnOutOfMemoryError 默认关闭，建议开启。在java.lang.OutOfMemoryError 异常出现时，输入一个dump文件，记录当时的堆内存快照。
 * -XX:HeapDumpPath=./java_pid<pid>.hprof 用来这只堆内存k快照的文件存储路径。默认是java进程启动未知。
 *
 * three：调优之前开启、调优之后关闭
 * -XX:PrintGC
 * 调试跟踪前 打印简单的gc日志参数：
 * -XX:+PrintGCDetail   -XX:+PrintGCTimeStamps
 * 打印详细的GC信息
 * -Xlogger:logpath
 * 设置GC的日志目录，如：-Xlogger:log/gc.log。将gc.log的路径设置到当前目录的log目录下
 * 应用场景：将gc日志独立写入日志文件。与业务日志分离，便于开发人员跟踪问题。
 *
 * four：考虑使用
 * -XX:+PrintHeapAtGC  打印堆信息
 * 参数设置：-XX:+PrintHeapAtGC
 * 应用场景：获取堆在每次垃圾回收前后的使用情况
 * -XX:+TraceClassLoading
 * 参数设置：-XX:+TraceClassLoading
 * 应用场景：在系统控制台信息中看到class加载的信息，可以分析类的加载顺序以及是否可进行精简操作。
 * -XX:+DisableExplicitGC 禁止在运行期间显示调用System.gc();这个命令消耗性能，生产环境不建议使用。
 *
 *
 * 二、可视化工具(有局限性，生产环境基本禁用，所以生产多会使用命令行工具)
 *
 * 1、Jconsole 大概看下就知道了
 * {@link com.my.jvm.utils.jconsole}
 *
 * 2、jvisualvm 大概看下就知道了
 *  {@link com.my.jvm.utils.jvisualvm}
 *
 * 三、arthas（阿尔萨斯）
 * {@link com.my.jvm.utils.arthas}
 */