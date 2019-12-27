package pers.yo.case1.util;

public class Demo06ClassPath1 {
    public static void main(String[] args) {
        //System.out.println( cl.getResource("") ); //这是什么？
        //file:/H:/ProcExes/JavaFiles/JavaDM/JavaAdvance1/out/production/JavaAdvance1/
        System.out.println (System.getProperty ("java.class.path")); //这又是什么？
        /* H:\ProcExes\JavaFiles\JavaDM\JavaAdvance1\out\production\JavaAdvance1 也在显示的结果中
        *
        * java 中的 classpath 是当前系统所涉及的 class 字节码的存放路径，不是一个、两个地方，
        * 是系统在编译时或运行时所有关联的字节码的路径。
        * 写个 main 方法，打印下这句话就可以看到所有的 classpath：
        * System.out.println (System.getProperty ("java.class.path");
        * 比较全的重要的几个应该就是：
        * ~/jdk/jre/lib/*.jar、
        * ~/jdk/jre/lib/ext/*.jar、
        * ~/maven/.m2/*.jar、
        * ~/target/classes/*.class 等
        *
        * 把包含该文件的文件夹标记为 “Source Root” 即可把该文件夹加入 classpath，
        * 已知，src文件夹已被我标记为  “Source Root”，所以 src文件夹也加入了 classpath中
        *
        * src也属于classpath的子文件夹，
        * 把配置文件 (如druid.properties)放在src文件夹下，然后通过 类_类型的 类加载器读取此配置文件后：
        * 此配置文件也会被复制到： out编译输出文件夹/production/JavaAdvance1(模块名)
        * 本质上，类加载器还是会直接读取 out编译输出文件夹/production/JavaAdvance1(模块名)下的资源文件！！
        *
        * 所以，以后把资源文件直接放在src文件夹 或 直接放在 out编译输出文件夹下，都是同理的
        * */

    }
}

