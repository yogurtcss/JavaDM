package pers.yo.adv1.demo26_Reflect;

/*
▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
关于资源载入：类加载器ClassLoader
java.lang.ClassLoader类，lang包下的直接子类，不用import

类加载器（class loader）用来加载Java类到 Java 虚拟机中。
一般来说，Java 虚拟机使用 Java 类的方式如下：
(1)Java 源程序(.java 文件)在经过 Java 编译器编译之后就被转换成 Java 字节代码(.class 文件)。
(2)类加载器 负责读取Java字节代码(*.class文件)，并转换成 java.lang.Class 类的一个实例。
每个这样的实例用来表示一个 Java 类。通过此实例的 newInstance () 方法就可以创建出该类的一个对象。

注意：类加载器 负责读取Java字节代码(*.class文件) 的；
所以，通过 【此Java类A的字节码文件的class类对象】下的 getClassLoader()方法，此功能就是
(反向)获取 那个【定义此Java类A】的类加载器 的实例对象
——通过我本身.class文件，反向找到【是哪个“类加载器”(的实例对象)】 读取我.class文件

▲ ClassLoader负责载入系统的所有资源 (Class，文件，图片，来自网络的字节流等)，通过 ClassLoader从而将资源载入 JVM 中。

--------------------------------------------------------
若要通过 编译后的.class文件或ClassLoader实例对象 下的getResource()方法 获取某个资源文件x.txt：
则此资源文件必须放在
* 编译后的.class所在的根目录，
  通常是在：【以你当前模块名】 命名的 编译输出的文件夹 为根目录，表示里面这一堆.class文件都是出自你这个模块的
  理解：因为里面这一堆.class文件都是出自你这个模块的，所以就 【以你当前模块名】 命名的 编译输出的文件夹 为根目录
  路径为：out总编译输出文件夹/production“生产”部分/【变化的部分：当前你java文件所在模块的名字】(即 CLASSPATH 的位置)

* 或 .class所在的根目录下的子目录 中；
(因为真正使用的就是.class文件，所以就约定 【以它所在的根目录】 为参照的起点)

▲ 所以，保存编译输出.class文件的文件夹，还是不要忽略(隐藏)它！！

若资源文件均不在以上两个目录中，则 不能通过 编译后的.class文件或ClassLoader实例对象 下的getResource()方法 获取，
只能通过此文件的 绝对地址 来获取了；

--------------------------------------------------------
通过某个类A的 .class文件，反向获取此类A的“类加载器”后-Class.getClassLoader()，再接着获取资源文件
Class.getClassLoader().getResource(String path) 即不以”/” 开头的路径，是从 ClassPath 根目录下取路径；

▲ ClassLoader().getResourceAsStream(String path) 和 ClassLoader.getResource(String path) 对“路径”的选择标准也是相同的

--------------------------------------------------------
路径规律总结：
1.Class.getResource (“”)，即不以”/” 开头的路径，是从当前 Class 类所在的包下取路径；
2.Class.getResource (“/”)，即以”/” 开头的路径，是从 ClassPath 根目录下取路径；

3.ClassLoader.getResource ()，即不以”/” 开头的路径，是从 ClassPath 根目录下取路径；
4.ClassLoader.getResource (“/”)，即以”/” 开头的路径，结果为 null；

5.Class.getResourceAsStream () 和 Class.getResource 对“路径”的选择标准是相同的；
6.ClassLoader().getResourceAsStream() 和 ClassLoader.getResource() 对“路径”的选择标准也是相同的

*  */

import java.io.IOException;
import java.io.InputStream;

public class Demo03ClassLoader {
    public static void main(String[] args) throws IOException {
        /* 类加载器ClassLoader 读取此类A对应的.class文件，然后创建本类的实例对象；
        * 那我通过 本类A的.class类对象(“类”类型的实例对象)，
        * 可反向找到 读取此我类的 某个类加载器
        *  */
        ClassLoader cld = Demo03ClassLoader.class.getClassLoader();
        System.out.println( "当前的ClassPath根为："+cld.getResource("") );
        //test1.txt放在 当前模块编译输出.class文件的 总文件中(这个总文件夹名为 模块名JavaAdvance1)
        InputStream is = cld.getResourceAsStream( "MyProperties/test1.txt" );
        byte[] rst = new byte[1024];
        int len = 0;
        while( (len=is.read(rst))!=-1 ){
            System.out.println( new String(rst) );
        }
    }
}
