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
 */