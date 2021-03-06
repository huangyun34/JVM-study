package com.my.jvm.utils.jstat;

/**
 * jstat 虚拟机统计信息监视工具
 *
 * 主要用途统计我们垃圾回收的情况、统计类加载情况等
 *
 * 1、统计类加载情况
 * jstat -class java进程id
 * 例子如下：
 * huangyundeMacBook-Pro:Downloads huangyun$ jstat -class 33388
 * Loaded  Bytes  Unloaded  Bytes     Time
 *    540  1091.7        0     0.0       0.10
 * Loaded：加载的类个数，Bytes：占用空间，Unloaded：没有加载的类个数，Bytes：占用空间，Time：耗费时间
 *
 * 2、统计垃圾回收情况（实时查看）
 * jstat -gc java进程id
 * 例子如下：
 * huangyundeMacBook-Pro:Downloads huangyun$ jstat -gc 33388
 *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * S0C：survival0(from)区的容量
 * S1C：survival1(to)区的容量
 * S0U：survival0(from)区的使用量
 * S1U：survival1(to)区的使用量
 * EC：eden区的容量
 * EU：eden区的使用量
 * OC：old区的容量
 * OU：old区的使用量
 * MC：方法区的容量
 * MU：方法去的使用量
 * CCSC：压缩类的容量
 * CCSU：压缩类的使用量
 * YGC：young GC的次数
 * YGCT：young GC所消耗的时间
 * FGC：full GC的次数
 * FGCT：full GC所消耗的时间
 * GCT：所有gc所消耗的时间
 *
 * 3、统计垃圾回收情况（统计）
 * jstat -gc java进程id 【打印的时间间隔（单位毫秒）】 【打印的次数】
 * 例子如下：
 * huangyundeMacBook-Pro:Downloads huangyun$ jstat -gc 33388 1000 10
 *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 * 5120.0 5120.0  0.0    0.0   33280.0   2662.6   87552.0      0.0     4480.0 774.9  384.0   75.9       0    0.000   0      0.000    0.000
 *
 *
 *
 */