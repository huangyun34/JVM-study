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
 *  1、jps 虚拟机进程状况工具
 * {@link com.my.jvm.utils.jps}
 *
 * 2、jstat 虚拟机统计信息监视工具
 * {@link com.my.jvm.utils.jstat}
 *
 * 3、jinfo java配置信息工具
 * {@link com.my.jvm.utils.jinfo}
 */