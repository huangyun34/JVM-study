package com.my.jvm.utils.jinfo;

/**
 * java配置信息工具 ，排查问题的关键，可动态修改运行时参数
 *
 * 1、java -XX:+PrintFlagsFinal -version 可以打印出所有高级运行时选项。
 * 例子如下：
 * huangyundeMacBook-Pro:Downloads huangyun$ java -XX:+PrintFlagsFinal -version
 * [Global flags]
 *     uintx AdaptiveSizeDecrementScaleFactor          = 4                                   {product}
 *     uintx AdaptiveSizeMajorGCDecayTimeScale         = 10                                  {product}
 *     uintx AdaptiveSizePausePolicy                   = 0                                   {product}
 *     uintx AdaptiveSizePolicyCollectionCostMargin    = 50                                  {product}
 *     uintx AdaptiveSizePolicyInitializingSteps       = 20                                  {product}
 *     uintx AdaptiveSizePolicyOutputInterval          = 0                                   {product}
 *     uintx AdaptiveSizePolicyWeight                  = 10                                  {product}
 *     uintx AdaptiveSizeThroughPutPolicy              = 0                                   {product}
 *     uintx AdaptiveTimeWeight                        = 25                                  {product}
 *      bool AdjustConcurrency                         = false                               {product}
 *      bool AggressiveOpts                            = false                               {product}
 *      intx AliasLevel                                = 3                                   {C2 product}
 *      bool AlignVector                               = false                               {C2 product}
 *      intx AllocateInstancePrefetchLines             = 1                                   {product}
 *      intx AllocatePrefetchDistance                  = 192                                 {product}
 *      intx AllocatePrefetchInstr                     = 3                                   {product}
 *      intx AllocatePrefetchLines                     = 4                                   {product}
 *      intx AllocatePrefetchStepSize                  = 64                                  {product}
 *      intx AllocatePrefetchStyle                     = 1                                   {product}
 *      bool AllowJNIEnvProxy                          = false                               {product}
 *      bool AllowNonVirtualCalls                      = false                               {product}
 *      bool AllowParallelDefineClass                  = false                               {product}
 *      bool AllowUserSignalHandlers                   = false                               {product}
 *      bool AlwaysActAsServerClassMachine             = false                               {product}
 *      bool AlwaysCompileLoopMethods                  = false                               {product}
 *      bool AlwaysLockClassLoader                     = false                               {product}
 *      bool AlwaysPreTouch                            = false                               {product}
 *      bool AlwaysRestoreFPU                          = false                               {product}
 *      bool AlwaysTenure                              = false                               {product}
 *      bool AssertOnSuspendWaitFailure                = false                               {product}
 *      bool AssumeMP                                  = false                               {product}
 *      intx AutoBoxCacheMax                           = 128                                 {C2 product}
 *     uintx AutoGCSelectPauseMillis                   = 5000                                {product}
 *      intx BCEATraceLevel                            = 0                                   {product}
 *      intx BackEdgeThreshold                         = 100000                              {pd product}
 *      bool BackgroundCompilation                     = true                                {pd product}
 *     uintx BaseFootPrintEstimate                     = 268435456                           {product}
 *      intx BiasedLockingBulkRebiasThreshold          = 20                                  {product}
 *      intx BiasedLockingBulkRevokeThreshold          = 40                                  {product}
 *      intx BiasedLockingDecayTime                    = 25000                               {product}
 *      intx BiasedLockingStartupDelay                 = 4000                                {product}
 * 第二列是默认值
 * 如果第三列是manageable则代表可以在运行时修改
 *
 * 2、jinfo -flags java进程id 显示出进程的jvm参数
 *  例子如下：
 *  由于mac问题，暂时无法简介，执行报错无法打印信息
 *
 * 3、jinfo -flag PrintGC java进程id 显示当前该参数的值
 * 例子如下：
 * huangyundeMacBook-Pro:/ huangyun$ jinfo -flag PrintGC 36314
 * -XX:-PrintGC
 * PrintGC前的"-"代表没有开启
 * 如上买的例子，如何修改呢，直接在PrintGC前加上"+"，jinfo -flag +PrintGC java进程id
 * 例子如下：
 * huangyundeMacBook-Pro:/ huangyun$ jinfo -flag +PrintGC 36654
 * huangyundeMacBook-Pro:/ huangyun$ jinfo -flag PrintGC 36654
 * -XX:+PrintGC
 *
 * 如上，如果想关掉的话，在前面加"-"就行了，jinfo -flag -PrintGC java进程id
 * 例子如下：
 * huangyundeMacBook-Pro:/ huangyun$ jinfo -flag -PrintGC 36654
 * huangyundeMacBook-Pro:/ huangyun$ jinfo -flag PrintGC 36654
 * -XX:-PrintGC
 */