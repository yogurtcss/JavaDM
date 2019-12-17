package pers.yo.adv1.demo26_Reflect;

/*
案例：
	* 需求：写一个"框架"，不能改变该类的任何代码的前提下，可以帮我们创建任意类的对象，并且执行其中任意方法
		* 实现：
			1. 配置文件( *.properties后缀的文件 )
			2. 反射
		* 步骤：
			1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
			2. 在程序中加载读取配置文件
			3. 使用反射技术来加载类文件进内存
			4. 创建对象
			5. 执行方法

----------------------------------------------------
关于 *.properties配置文件 和 类加载器 的研究，见本包下的相应代码文件哈

----------------------------------------------------
若要通过 编译后的.class文件或ClassLoader实例对象 下的getResource()方法 获取某个资源文件x.txt：
则此资源文件必须放在
* 编译后的.class所在的根目录，
  通常是在：【以你当前模块名】 命名的 编译输出的文件夹 为根目录，表示里面这一堆.class文件都是出自你这个模块的
  理解：因为里面这一堆.class文件都是出自你这个模块的，所以就 【以你当前模块名】 命名的 编译输出的文件夹 为根目录
  路径为：out总编译输出文件夹/production“生产”部分/【变化的部分：当前你java文件所在模块的名字】(即 CLASSPATH 的位置)

* 或 .class所在的根目录下的子目录 中；
(因为真正使用的就是.class文件，所以就约定 【以它所在的根目录】 为参照的起点)

若资源文件均不在以上两个目录中，则 不能通过 编译后的.class文件或ClassLoader实例对象 下的getResource()方法 获取，
只能通过此文件的 绝对地址 来获取了；

*  */

import java.util.Properties; //唯一与IO流结合的集合
import java.io.InputStream; //输入流
import java.io.IOException; //我直接声明一个 大的异常了

import java.lang.reflect.Constructor; //类加载器 封装好的构造方法
import java.lang.reflect.Method; //类加载器 封装好的方法

public class Demo03Framework1 {
    public static void main(String[] args) throws Exception {
        ClassLoader cld = Demo03Framework1.class.getClassLoader(); //获取本类的“类_类型”实例对象的 类加载器
        InputStream is = cld.getResourceAsStream( "MyProperties/framework1.properties" ); //以字节输入流形式读入数据

        Properties prop = new Properties();
        prop.load( is );
        String className = prop.getProperty( "className" );  //得到此类的“全类名：包名.类名”
        String methodName = prop.getProperty( "methodName" ); //得到此类的一个成员方法名
        String name = prop.getProperty( "name" );

        //注意，年龄为int型的，直接 prop.getProperty("age") 获取的是 字符串型的值
        // String one = prop.getProperty("age");
        int age = Integer.parseInt( prop.getProperty("age") );

        /* 获取此类的 类_类型(Class) 实例对象cls
        * 当配置文件时：将类名写在此配置文件中，读取此配置文件的 “全类名：包名.类名”，
        * 然后通过 Class类的静态方法 Class.forName(全类名) 即可获取此类的 类_类型(Class) 实例对象cls
        *  */
        Class cls = Class.forName( className ); //在这里，我直接声明一个大的异常了

        System.out.println( "--------------利用无参构造方法，创建的实例对象oVoid" );
        Constructor consVoid = cls.getConstructor();
        Object oVoid = consVoid.newInstance();
        Object oVoid2 = cls.newInstance();

        Method method = cls.getMethod( methodName );
        method.invoke( oVoid );
        method.invoke( oVoid2 );

        System.out.println( "--------------利用有参构造方法，按顺序形参为String、int，创建的实例对象o" );
        Constructor cons = cls.getConstructor( String.class, int.class );
        Object o = cons.newInstance( name, age );
        System.out.println( method.invoke(o) ); //getName方法

    }
}
