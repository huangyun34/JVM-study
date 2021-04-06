package com.my.jvm.utils.jmap;

/**
 * dump日志、heap dump日志，生成堆转储快照
 *
 * 1、jmap -heap Java进程id    打印堆全部的情况
 * mac jdk1.8暂时有bug，会报错，可考虑升级jdk
 * 例子如下：
 * [tm@zd-pre01 ~]$ jmap -heap 3244
 * Attaching to process ID 3244, please wait...
 * Debugger attached successfully.
 * Server compiler detected.
 * JVM version is 25.191-b12
 *
 * using thread-local object allocation.
 * Parallel GC with 4 thread(s)
 *
 * Heap Configuration:   （堆的配置情况）
 *    MinHeapFreeRatio         = 0
 *    MaxHeapFreeRatio         = 100
 *    MaxHeapSize              = 1073741824 (1024.0MB)
 *    NewSize                  = 178782208 (170.5MB)
 *    MaxNewSize               = 357564416 (341.0MB)
 *    OldSize                  = 358088704 (341.5MB)
 *    NewRatio                 = 2
 *    SurvivorRatio            = 8
 *    MetaspaceSize            = 21807104 (20.796875MB)
 *    CompressedClassSpaceSize = 1073741824 (1024.0MB)
 *    MaxMetaspaceSize         = 17592186044415 MB
 *    G1HeapRegionSize         = 0 (0.0MB)
 *
 * Heap Usage:     （堆实际适应情况）
 * PS Young Generation
 * Eden Space:
 *    capacity = 108003328 (103.0MB)
 *    used     = 39401816 (37.576499938964844MB)
 *    free     = 68601512 (65.42350006103516MB)
 *    36.48203877569402% used
 * From Space:
 *    capacity = 15728640 (15.0MB)
 *    used     = 15276288 (14.568603515625MB)
 *    free     = 452352 (0.431396484375MB)
 *    97.1240234375% used
 * To Space:
 *    capacity = 37224448 (35.5MB)
 *    used     = 0 (0.0MB)
 *    free     = 37224448 (35.5MB)
 *    0.0% used
 * PS Old Generation
 *    capacity = 358088704 (341.5MB)
 *    used     = 298802544 (284.96031188964844MB)
 *    free     = 59286160 (56.53968811035156MB)
 *    83.44372236885752% used
 *
 * 35015 interned Strings occupying 3632864 bytes.
 *
 *
 * 2、jmap -histo 进程ID 排查常用命令，它可以打印所有的历史包括存活的类实例的数量，并按实例大小倒叙排列。可以查看实例占用内存情况。
 * 这个在使用过程中比dump要好用，因为dump需要导出文件会大量消耗性能，而这个命令则只打印当前时刻的情况，不次奥好性能。
 * 例子如下：
 * [tm@zd-pre01 ~]$ jmap -histo 3244
 *
 *  num     #instances         #bytes  class name
 * ----------------------------------------------
 *    1:         47581      114014744  [B                                  解释下：如果是有[ 代表是数组，比如[B就是指Byte数组。
 *    2:       1404402       44940864  java.util.HashMap$Node
 *    3:        497308       37907632  [C
 *    4:        613104       19619328  com.mysql.cj.conf.BooleanProperty
 *    5:         48770       15765888  [Ljava.util.HashMap$Node;
 *    6:        461148       11067552  java.lang.String
 *    7:        329688       10550016  com.mysql.cj.conf.StringProperty
 *    8:         35026        9527056  [I
 *    9:        179304        5737728  com.mysql.cj.conf.IntegerProperty
 *   10:        102928        5460616  [Ljava.lang.Object;
 *   11:         53055        4668840  java.lang.reflect.Method
 *   12:         75206        2406592  java.util.concurrent.ConcurrentHashMap$Node
 *   13:         47495        2279760  java.util.HashMap
 *   14:         16055        1790320  java.lang.Class
 *   15:         53536        1713152  java.util.Hashtable$Entry
 *   16:         56094        1346256  java.util.ArrayList
 *   17:         16001        1297944  [Ljava.util.WeakHashMap$Entry;
 *   18:         35018        1120576  java.lang.ref.WeakReference
 *   19:         34704        1110528  com.mysql.cj.conf.EnumProperty
 *   20:          5784        1064256  com.mysql.cj.jdbc.ConnectionImpl
 *   21:         46895        1037168  [Ljava.lang.Class;
 *   22:         25312        1012480  java.util.LinkedHashMap$Entry
 *   23:         15806        1011584  org.springframework.boot.loader.jar.JarFileWrapper
 *   24:         61823         989168  java.lang.Object
 *
 * 3、jmap -histo:live 进程ID 相较于2，多加了一个live，区别在于它只打印现在存活的类实例的数量
 *
 * 4、jmap -histo:live 进程ID |head -20 显示前20条
 *
 * 5、jmap -dump:live,format=b,file=/Users/huangyun/heap.bin 进程ID  生成存活堆转储快照。（注意，磁盘不够，不要用，会爆磁盘）
 * 例子如下：
 * huangyundeMacBook-Pro:~ huangyun$ jmap -dump:live,format=b,file=/Users/huangyun/heap.bin 44657
 * Dumping heap to /Users/huangyun/heap.bin ...
 * Heap dump file created [3565225 bytes in 0.077 secs]
 *
 *
 */