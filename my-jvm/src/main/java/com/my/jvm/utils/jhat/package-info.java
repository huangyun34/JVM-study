package com.my.jvm.utils.jhat;

/**
 * 分析的命令行工具，不推荐使用,会占用大量内存。建议使用可视化工具，比如JConsole\VisualVM等
 *
 * 1、jhat dump日志路径    好了后看描述，我们就可以打开浏览器输入localhost:7000 ，来查看了。但是这个东西过于专业，不利于查看，所以用其他工具代替
 * 例子如下：
 * huangyundeMacBook-Pro:~ huangyun$ jhat heap.bin
 * Reading from heap.bin...
 * Dump file created Fri Apr 02 14:45:33 CST 2021
 * Snapshot read, resolving...
 * Resolving 24902 objects...
 * Chasing references, expect 4 dots....
 * Eliminating duplicate references....
 * Snapshot resolved.
 * Started HTTP server on port 7000
 * Server is ready.
 *
 *
 */