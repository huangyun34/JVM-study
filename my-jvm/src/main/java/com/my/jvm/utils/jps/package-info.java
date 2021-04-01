package com.my.jvm.utils.jps;

/**
 * jps为虚拟机进程状况工具
 *
 *
 * 1、jps 显示进程和名称
 * 例子如下：
 * 32689 Jps
 * 27954 RemoteMavenServer
 * 26850 Launcher
 * 30596 Launcher
 * 26837 Launcher
 * 26806
 * 31895 Launcher
 *
 * 2、jps -q 只显示进程id
 * 例子如下：
 * 32706
 * 27954
 * 26850
 * 30596
 * 26837
 * 26806
 * 31895
 *
 * 3、jps -m 显示进程、名称、参数
 * 例子如下：
 * 27954 RemoteMavenServer
 * 26850 Launcher /Applications/IntelliJ IDEA.app/Contents/lib/lz4-java-1.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/javac2.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jgoodies-forms.jar:/Applications/IntelliJ IDEA.app/Contents/lib/asm-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/commons-codec-1.9.jar:/Applications/IntelliJ IDEA.app/Contents/lib/guava-21.0.jar:/Applications/IntelliJ IDEA.app/Contents/lib/httpcore-4.4.5.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna-platform.jar:/Applications/IntelliJ IDEA.app/Contents/lib/oro-2.0.8.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jps-model.jar:/Applications/IntelliJ IDEA.app/Contents/lib/util.jar:/Applications/IntelliJ IDEA.app/Contents/lib/platform-api.jar:/Applications/IntelliJ IDEA.app/Contents/lib/slf4j-api-1.7.10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/aether-1.1.0-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/snappy-in-java-0.5.1.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna.jar:/Applica
 * 30596 Launcher /Applications/IntelliJ IDEA.app/Contents/lib/lz4-java-1.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/javac2.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jgoodies-forms.jar:/Applications/IntelliJ IDEA.app/Contents/lib/asm-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/commons-codec-1.9.jar:/Applications/IntelliJ IDEA.app/Contents/lib/guava-21.0.jar:/Applications/IntelliJ IDEA.app/Contents/lib/httpcore-4.4.5.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna-platform.jar:/Applications/IntelliJ IDEA.app/Contents/lib/oro-2.0.8.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jps-model.jar:/Applications/IntelliJ IDEA.app/Contents/lib/util.jar:/Applications/IntelliJ IDEA.app/Contents/lib/platform-api.jar:/Applications/IntelliJ IDEA.app/Contents/lib/slf4j-api-1.7.10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/aether-1.1.0-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/snappy-in-java-0.5.1.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna.jar:/Applica
 * 26837 Launcher /Applications/IntelliJ IDEA.app/Contents/lib/lz4-java-1.3.jar:/Applications/IntelliJ IDEA.app/Contents/lib/javac2.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jgoodies-forms.jar:/Applications/IntelliJ IDEA.app/Contents/lib/asm-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/commons-codec-1.9.jar:/Applications/IntelliJ IDEA.app/Contents/lib/guava-21.0.jar:/Applications/IntelliJ IDEA.app/Contents/lib/httpcore-4.4.5.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna-platform.jar:/Applications/IntelliJ IDEA.app/Contents/lib/oro-2.0.8.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jps-model.jar:/Applications/IntelliJ IDEA.app/Contents/lib/util.jar:/Applications/IntelliJ IDEA.app/Contents/lib/platform-api.jar:/Applications/IntelliJ IDEA.app/Contents/lib/slf4j-api-1.7.10.jar:/Applications/IntelliJ IDEA.app/Contents/lib/aether-1.1.0-all.jar:/Applications/IntelliJ IDEA.app/Contents/lib/snappy-in-java-0.5.1.jar:/Applications/IntelliJ IDEA.app/Contents/lib/jna.jar:/Applica
 *
 * 4、jps -l 显示应用主程序的所在的类的完整路径
 * 例子如下：
 * 27954 org.jetbrains.idea.maven.server.RemoteMavenServer
 * 26850 org.jetbrains.jps.cmdline.Launcher
 * 30596 org.jetbrains.jps.cmdline.Launcher
 * 26837 org.jetbrains.jps.cmdline.Launcher
 * 26806
 * 31895 org.jetbrains.jps.cmdline.Launcher
 * 33031 sun.tools.jps.Jps
 *
 * 5、jps -v 显示jvm的参数
 * 例子如下：
 * 27954 RemoteMavenServer -Djava.awt.headless=true -Didea.version==2018.1 -Xmx768m -Didea.maven.embedder.version=3.5.3 -Dfile.encoding=UTF-8
 * 26850 Launcher -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=/Users/huangyun/IdeaProjects/newdemo -Dpreload.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1/options -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=8918538726666880563 -Dfile.encoding=UTF-8 -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2018.1 -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1 -Didea.plugins.path=/Users/huangyun/Library/Application Support/IntelliJIdea2018.1 -Djps.log.dir=/Users/huangyun/Library/Logs/IntelliJIdea2018.1/build-log -Djps.fallback.jdk.home=/Applications/IntelliJ IDEA.app/Contents/jdk/Contents/Home/jre -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=/Users/huangyun/Library/Caches/IntelliJIdea2018.1/compile-server/newdemo_676c4
 * 30596 Launcher -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=/Users/huangyun/IdeaProjects/land -Dpreload.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1/options -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=8918538726666880563 -Dfile.encoding=UTF-8 -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2018.1 -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1 -Didea.plugins.path=/Users/huangyun/Library/Application Support/IntelliJIdea2018.1 -Djps.log.dir=/Users/huangyun/Library/Logs/IntelliJIdea2018.1/build-log -Djps.fallback.jdk.home=/Applications/IntelliJ IDEA.app/Contents/jdk/Contents/Home/jre -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=/Users/huangyun/Library/Caches/IntelliJIdea2018.1/compile-server/land_3432fb4b/_t
 * 26837 Launcher -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=/Users/huangyun/IdeaProjects/mybatis-study -Dpreload.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1/options -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=8918538726666880563 -Dfile.encoding=UTF-8 -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2018.1 -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1 -Didea.plugins.path=/Users/huangyun/Library/Application Support/IntelliJIdea2018.1 -Djps.log.dir=/Users/huangyun/Library/Logs/IntelliJIdea2018.1/build-log -Djps.fallback.jdk.home=/Applications/IntelliJ IDEA.app/Contents/jdk/Contents/Home/jre -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=/Users/huangyun/Library/Caches/IntelliJIdea2018.1/compile-server/mybatis
 * 26806  -Xms128m -Xmx750m -XX:ReservedCodeCacheSize=240m -XX:+UseCompressedOops -Dfile.encoding=UTF-8 -XX:+UseConcMarkSweepGC -XX:SoftRefLRUPolicyMSPerMB=50 -ea -Dsun.io.useCanonCaches=false -Djava.net.preferIPv4Stack=true -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Xverify:none -XX:ErrorFile=/Users/huangyun/java_error_in_idea_%p.log -XX:HeapDumpPath=/Users/huangyun/java_error_in_idea.hprof -Djb.vmOptionsFile=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1/idea.vmoptions -Didea.java.redist=jdk-bundled -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.executable=idea -Didea.paths.selector=IntelliJIdea2018.1
 * 33126 Jps -Denv.class.path=.:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/lib:/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home/jre/lib -Dapplication.home=/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home -Xms8m
 * 31895 Launcher -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=/Users/huangyun/IdeaProjects/JVM-study -Dpreload.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1/options -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=8918538726666880563 -Dfile.encoding=UTF-8 -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2018.1 -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.config.path=/Users/huangyun/Library/Preferences/IntelliJIdea2018.1 -Didea.plugins.path=/Users/huangyun/Library/Application Support/IntelliJIdea2018.1 -Djps.log.dir=/Users/huangyun/Library/Logs/IntelliJIdea2018.1/build-log -Djps.fallback.jdk.home=/Applications/IntelliJ IDEA.app/Contents/jdk/Contents/Home/jre -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=/Users/huangyun/Library/Caches/IntelliJIdea2018.1/compile-server/jvm-study_7
 *
 */