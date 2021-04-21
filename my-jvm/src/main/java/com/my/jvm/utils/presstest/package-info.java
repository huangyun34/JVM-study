package com.my.jvm.utils.presstest;


/**
 * 压测工具：Apache Bench
 *
 * Apache Bench是Apache 自带的压力测试工具，以下链接是在mac系统安装的步骤，亲测可用：
 * https://blog.csdn.net/lzf_hlh/article/details/104948548
 * 安装中发现，缺少了APR-util
 * 在brew install apr之后
 * 还要下载APR-util,去官网下载
 *
 * 常用命令
 * ab -c 10 -n 100 http://localhost:8080/adminapi/sso/login   GET请求
 * ab -c 10 -n 100 -p 'post.txt' -T 'application/x-www-form-urlencoded' 'http://localhost:8080/adminapi/sso/login'   POST请求
 * 需要把参数放入post.txt文件中
 *
 * 例子如下：
 * 几个重要指标描述：
* Concurrency Level       10                                                        并发量
 * Requests per second:    976.35 [#/sec] (mean)                                    吞吐量
 * Time per request:       10.242 [ms] (mean)                                       用户的平均请求时间，总的
 * Time per request:       1.024 [ms] (mean, across all concurrent requests)        服务器处理时间
 * Percentage of the requests served within a certain time (ms)                     多少比例的请求，他的时间在哪个范围之内
 * 例子：50%      9      50%的请求在9毫秒之内
 *      66%     10      66%的请求在10毫秒之内
 *
 * huangyundeMacBook-Pro:bin huangyun$ ab -c 10 -n 100 http://localhost:8080/adminapi/sso/login
 * This is ApacheBench, Version 2.3 <$Revision: 1807734 $>
 * Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
 * Licensed to The Apache Software Foundation, http://www.apache.org/
 *
 * Benchmarking localhost (be patient).....done
 *
 *
 * Server Software:
 * Server Hostname:        localhost
 * Server Port:            8080
 *
 * Document Path:          /adminapi/sso/login
 * Document Length:        236 bytes
 *
 * Concurrency Level:      10
 * Time taken for tests:   0.102 seconds
 * Complete requests:      100
 * Failed requests:        0
 * Total transferred:      34100 bytes
 * HTML transferred:       23600 bytes
 * Requests per second:    976.35 [#/sec] (mean)
 * Time per request:       10.242 [ms] (mean)
 * Time per request:       1.024 [ms] (mean, across all concurrent requests)
 * Transfer rate:          325.13 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    1   1.5      1       6
 * Processing:     2    9   4.0      8      19
 * Waiting:        0    8   4.3      7      19
 * Total:          4   10   3.8      9      20
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%      9
 *   66%     10
 *   75%     12
 *   80%     12
 *   90%     16
 *   95%     19
 *   98%     20
 *   99%     20
 *  100%     20 (longest request)
 *
 *
 *  案例分析：
 *  注释：所有项目都在newDemo项目中，不放在本项目中进行
 *  1、  模拟秒杀 参考Case1Controller类中的connect方法
 *  压测的目的：检查垃圾回收的性能
 *
 *  分析流程
 *  1）查看机器堆空间使用情况jvm有个命令可以查看
 *  java -XX:+PrintFlagsFinal -version |grep HeapSize
 *
 *  说明：
 *  InitialHeapSize  堆初始空间
 *  MaxHeapSize      最大堆空间
 *
 *  例子如下：
 *  huangyundeMacBook-Pro:bin huangyun$ java -XX:+PrintFlagsFinal -version |grep HeapSize
 *     size_t ErgoHeapSizeLimit                        = 0                                         {product} {default}
 *     size_t HeapSizePerGCThread                      = 43620760                                  {product} {default}
 *     size_t InitialHeapSize                          = 134217728                                 {product} {ergonomic}
 *     size_t LargePageHeapSizeThreshold               = 134217728                                 {product} {default}
 *     size_t MaxHeapSize                              = 2147483648                                {product} {ergonomic}
 *     size_t MinHeapSize                              = 8388608                                   {product} {ergonomic}
 *      uintx NonNMethodCodeHeapSize                   = 5832780                                {pd product} {ergonomic}
 *      uintx NonProfiledCodeHeapSize                  = 122912730                              {pd product} {ergonomic}
 *      uintx ProfiledCodeHeapSize                     = 122912730                              {pd product} {ergonomic}
 *     size_t SoftMaxHeapSize                          = 2147483648                             {manageable} {ergonomic}
 *  java version "15.0.1" 2020-10-20
 *  Java(TM) SE Runtime Environment (build 15.0.1+9-18)
 *  Java HotSpot(TM) 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)
 *
 *  2）使用jmap查看具体进程对空间情况
 *  jmap -heap pid
 *  例子如下：
 *
 * 3）GC状态 由于我们更关心YGC     YGCT    FGC    FGCT     GCT，所以加入过滤条件，只显示后面几列
 * jstat -gc pid 5000 20 |awk '{print $13,$14,$15,$16,$17}'
 *
 * 在默认垃圾回收器下，gc是需要stop the world，所以要尽量减少
 *
 *
 * 进行压测：
 * 开始前要做一次热身，跑第一个，但是不做记录
 * ******************************************************************************
 * 1、10个并发用户/10万次请求（总）
 *
 * ab -c 10 -n 100000 -k http://localhost:8080/case1/connect
 *
 * ====================================================
 * 默认情况：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 1077 9.135 7 1.112 10.247
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 1729 16.535 11 1.662 18.197
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 652 7.400 4 0.550 7.950
 *
 *
 *
 * Concurrency Level:      10
 * Time taken for tests:   43.495 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99005
 * Total transferred:      14395025 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    2299.13 [#/sec] (mean)
 * Time per request:       4.349 [ms] (mean)
 * Time per request:       0.435 [ms] (mean, across all concurrent requests)
 * Transfer rate:          323.20 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.1      0       3
 * Processing:     1    4  12.0      2     550
 * Waiting:        0    4  12.0      2     550
 * Total:          1    4  12.0      2     550
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%      2
 *   66%      3
 *   75%      3
 *   80%      4
 *   90%      6
 *   95%     13
 *   98%     25
 *   99%     49
 *  100%    550 (longest request)
 *  ====================================================
 *  ====================================================
 * 优化方案1：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 529 5.101 3 0.454 5.555
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 1058 18.611 3 0.454 19.065
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 529 13.510 0 0.000 13.510
 *
 *
 *
 * Concurrency Level:      10
 * Time taken for tests:   47.599 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99004
 * Total transferred:      14395020 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    2100.87 [#/sec] (mean)
 * Time per request:       4.760 [ms] (mean)
 * Time per request:       0.476 [ms] (mean, across all concurrent requests)
 * Transfer rate:          295.33 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.0      0       2
 * Processing:     1    5  12.4      2     291
 * Waiting:        0    5  12.4      2     291
 * Total:          1    5  12.4      2     291
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%      2
 *   66%      3
 *   75%      3
 *   80%      4
 *   90%      5
 *   95%     21
 *   98%     36
 *   99%     60
 *  100%    291 (longest request)
 *  ====================================================
 *  ====================================================
 * 优化方案2：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 253 1.621 3 0.407 2.029
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 503 3.244 3 0.407 3.651
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 250 1.623 0 0.000 1.623
 *
 *
 *
 * Concurrency Level:      10
 * Time taken for tests:   37.266 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99006
 * Total transferred:      14395030 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    2683.44 [#/sec] (mean)
 * Time per request:       3.727 [ms] (mean)
 * Time per request:       0.373 [ms] (mean, across all concurrent requests)
 * Transfer rate:          377.23 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.1      0       3
 * Processing:     1    4   8.1      2     308
 * Waiting:        0    4   8.1      2     308
 * Total:          1    4   8.1      2     308
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%      2
 *   66%      3
 *   75%      3
 *   80%      4
 *   90%      5
 *   95%      8
 *   98%     17
 *   99%     31
 *  100%    308 (longest request)
 *  ====================================================
 *
 * ******************************************************************************
 * 2、100个并发用户/10万次请求（总）
 *
 * ab -c 100 -n 100000 -k http://localhost:8080/case1/connect
 *
 * ====================================================
 * 默认情况：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 1834 17.613 11 1.662 19.275
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 2495 26.219 20 2.615 28.835
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 661 8.606 9 0.953 9.560
 *
 *
 *
 * Concurrency Level:      100
 * Time taken for tests:   48.184 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99050
 * Total transferred:      14395250 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    2075.40 [#/sec] (mean)
 * Time per request:       48.184 [ms] (mean)
 * Time per request:       0.482 [ms] (mean, across all concurrent requests)
 * Transfer rate:          291.76 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.4      0      42
 * Processing:     0   48  40.0     46     537
 * Waiting:        0   48  40.0     46     537
 * Total:          0   48  40.0     46     541
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%     46
 *   66%     57
 *   75%     65
 *   80%     69
 *   90%     86
 *   95%    110
 *   98%    151
 *   99%    193
 *  100%    541 (longest request)
 * ====================================================
 *  ====================================================
 * 优化方案1：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 1058 18.611 3 0.454 19.065
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 1598 48.582 4 0.854 49.436
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 540 29.971 1 0.400 30.371
 *
 *
 *
 * Concurrency Level:      100
 * Time taken for tests:   66.416 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99050
 * Total transferred:      14395250 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    1505.66 [#/sec] (mean)
 * Time per request:       66.416 [ms] (mean)
 * Time per request:       0.664 [ms] (mean, across all concurrent requests)
 * Transfer rate:          211.66 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.4      0      37
 * Processing:     1   66  75.1     46    1174
 * Waiting:        0   66  75.1     45    1174
 * Total:          1   66  75.1     46    1174
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%     46
 *   66%     73
 *   75%     92
 *   80%    106
 *   90%    143
 *   95%    188
 *   98%    283
 *   99%    371
 *  100%   1174 (longest request)
 *  ====================================================
 *  ====================================================
 * 优化方案2：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 503 3.244 3 0.407 3.651
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 755 10.399 3 0.407 10.806
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 252 7.155 0 0.000 7.155
 *
 *
 *
 * Concurrency Level:      100
 * Time taken for tests:   54.818 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99048
 * Total transferred:      14395240 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    1824.20 [#/sec] (mean)
 * Time per request:       54.818 [ms] (mean)
 * Time per request:       0.548 [ms] (mean, across all concurrent requests)
 * Transfer rate:          256.44 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   2.6      0     124
 * Processing:     1   55  55.5     42    1684
 * Waiting:        0   54  55.4     41    1684
 * Total:          1   55  55.5     42    1684
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%     42
 *   66%     59
 *   75%     72
 *   80%     80
 *   90%    103
 *   95%    132
 *   98%    193
 *   99%    250
 *  100%   1684 (longest request)
 *  ====================================================
 *
 *
 * ******************************************************************************
 * 3、1000个并发用户/10万次请求（总）
 *
 * ab -c 1000 -n 100000 -k http://localhost:8080/case1/connect
 *
 * ====================================================
 * 默认情况：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 2495 26.219 20 2.615 28.835
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 3179 41.139 44 6.347 47.486
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 684 14.920 24 3.732 18.652
 *
 *
 *
 * Concurrency Level:      1000
 * Time taken for tests:   56.804 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99460
 * Total transferred:      14397300 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    1760.44 [#/sec] (mean)
 * Time per request:       568.040 [ms] (mean)
 * Time per request:       0.568 [ms] (mean, across all concurrent requests)
 * Transfer rate:          247.52 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    1   9.1      0     254
 * Processing:     1  564 328.6    484    4094
 * Waiting:        1  564 327.4    484    3450
 * Total:          1  565 329.8    484    4094
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%    484
 *   66%    586
 *   75%    661
 *   80%    710
 *   90%    890
 *   95%   1146
 *   98%   1563
 *   99%   1917
 *  100%   4094 (longest request)
 * ====================================================
 *  ====================================================
 * 优化方案1：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 1598 48.582 4 0.854 49.436
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 2162 92.451 10 2.458 94.908
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 564 43.869 6 1.604 45.472
 *
 *
 *
 * Concurrency Level:      1000
 * Time taken for tests:   82.561 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99499
 * Total transferred:      14397495 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    1211.22 [#/sec] (mean)
 * Time per request:       825.612 [ms] (mean)
 * Time per request:       0.826 [ms] (mean, across all concurrent requests)
 * Transfer rate:          170.30 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    1   5.5      0      74
 * Processing:     1  820 550.2    707    6024
 * Waiting:        0  819 550.0    707    6024
 * Total:          1  820 551.2    707    6024
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%    707
 *   66%    866
 *   75%    996
 *   80%   1095
 *   90%   1430
 *   95%   1812
 *   98%   2437
 *   99%   2843
 *  100%   6024 (longest request)
 *  ====================================================
 *  ====================================================
 * 优化方案2：
 * gc情况起始：
 * YGC YGCT FGC FGCT GCT
 * 253 1.834 3 0.314 2.148
 * 运行完成后：
 * YGC YGCT FGC FGCT GCT
 * 528 15.613 17 3.591 19.205
 *
 * 相减
 * YGC YGCT FGC FGCT GCT
 * 275 14.779 14 3.277 17.057
 *
 *
 *
 * Concurrency Level:      1000
 * Time taken for tests:   54.139 seconds
 * Complete requests:      100000
 * Failed requests:        0
 * Keep-Alive requests:    99459
 * Total transferred:      14397295 bytes
 * HTML transferred:       700000 bytes
 * Requests per second:    1847.09 [#/sec] (mean)
 * Time per request:       541.392 [ms] (mean)
 * Time per request:       0.541 [ms] (mean, across all concurrent requests)
 * Transfer rate:          259.70 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    1   7.6      0     127
 * Processing:     1  538 408.0    465    4349
 * Waiting:        0  538 408.0    464    4349
 * Total:          1  539 412.1    465    4410
 *
 * Percentage of the requests served within a certain time (ms)
 *   50%    465
 *   66%    544
 *   75%    618
 *   80%    663
 *   90%    831
 *   95%   1101
 *   98%   1680
 *   99%   2608
 *  100%   4410 (longest request)
 *  ====================================================
 *
 *
 *
 *
 *
 *
 *
 *                                                  10个并发数      100个并发数          1000个并发数
 *  默认情况     吞吐量                                2299.13         2075.40             1760.44
 *              平均处理时间（服务器时间）               0.435 [ms]      0.482 [ms]          0.568 [ms]
 *              GC耗时                                7.950s          9.560               18.652
 *
 *  优化方案1    吞吐量                                2100.87         1505.66             1211.22
 *              平均处理时间（服务器时间）               0.476 [ms]      0.664 [ms]          0.826 [ms]
 *              GC耗时                                13.510          30.371              45.472
 *
 *  优化方案2    吞吐量                                2683.44         1824.20             1847.09
 *              平均处理时间（服务器时间）               0.373 [ms]      0.548 [ms]          0.541 [ms]
 *              GC耗时                                1.623           7.155               17.057
 *
 *  注释：
 *  优化方案1：简单粗暴堆加大：-Xms2500m   -Xmx2500m
 *  优化方案2：在堆和之前一样打的情况下，再去调大我们的eden用到-Xmn,再修改eden区在新生代中的占比，SurvivorRatio=8代表占新生代的8/10：-Xms2500m   -Xmx2500m -Xmn1700m -XX:SurvivorRatio=8
 *
 */