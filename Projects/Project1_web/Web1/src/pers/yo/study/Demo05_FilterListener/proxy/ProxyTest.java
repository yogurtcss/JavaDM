package pers.yo.study.Demo05_FilterListener.proxy;

/* 支线任务：设计模式之代理模式
之前的分析：
1.对request对象进行增强。增强获取参数相关方法
2.放行。传递代理对象
------------------------------------
如何增强对象的功能？

设计模式：一些通用的解决固定问题的方式
1.装饰模式
2.代理模式
------------------------------------
以下详解【代理模式】

一、概念：
1.真实对象：被代理的对象
2.代理对象：
3.代理模式：代理对象代理真实对象，达到增强真实对象功能的目的

二、实现方式：
1.静态代理：有一个类文件描述代理模式
2.动态代理(dynamic proxy)：在内存中形成代理类

-----------------
▲ 什么是动态代理 (dynamic proxy)？
利用 Java 的反射技术 (Java Reflection)，
在运行时 创建一个 实现【某些给定接口】的新类 (也称 “动态代理类”) 及 此实现类的实例对象
--简单粗暴：动态代理就是实现 InvocationHandler 接口

▲ 动态代理有什么用
1.解决特定问题：一个接口的实现在编译时无法知道，需要在运行时才能实现
2.实现某些设计模式：适配器 (Adapter) 或修饰器 (Decorator)
3.面向切面编程：如 AOP in Spring
-----------------

三、实现步骤：
1.代理对象和真实对象实现相同的接口
2.代理对象 = Proxy.newProxyInstance();
3.使用代理对象调用方法。
  -因为代理对象与真实对象实现了相同的接口，所以代理对象与真实对象有相同的方法可调用
4.增强方法

*  */

import pers.yo.study.Demo05_FilterListener.proxy.SaleComputer;
import pers.yo.study.Demo05_FilterListener.proxy.Lenovo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //----------1.创建真实对象(被代理的对象)
        Lenovo lenovo = new Lenovo();


        //----------2.动态代理，以增强 lenovo实例对象
       /*
       ▲ 任意实例对象.getClass()：通过实例对象中的getClass()方法，返回class 类_类型的实例对象
       此getClass() 方法 已在Object类中定义了，任何实例对象（默认继承了Object类）都拥有这个方法
       ▲ 类名.class属性：通过类名的属性class获取，返回class 类_类型的实例对象
       如接口类名为 SaleComputer，获取此接口的类_类型实例对象 SaleComputer类名.class

       --------------------------------------------------
       ▲ java.lang.reflect.Proxy类下的静态方法：

       public static Object newProxyInstance(  //返回值是 Object类型的；真正使用时要 向下转型为自己需要的类型嗷(如 接口类型)！
          ClassLoader loader,      -真实对象(被代理的对象)的类加载器

          Class<?>[] interfaces,   -【Class类_类型的数组，带泛型<?>哦！！】数组元素是 【要实现的接口】的 class类_类型 实例对象
                                     //泛型通配符 ?
                                   -指定 newProxyInstance()方法返回的对象要实现【哪些接口】，即可以指定多个接口

                                   -在动态代理中：代理对象和真实对象实现相同的接口！！所以接口数组必需传入 【真实对象A已实现的接口】
                                      ▲若需传入的接口是 【真实对象A已实现的接口】，可以直接从此对象A中拿到这个Class<?>[]类型的接口数组
                                     -真实对象A.getClass().getInterfaces()

          InvocationHandler接口类型 h   -【处理器】所有对动态代理对象的方法调用，都会被转向到 InvocationHandler实现类的实例对象h上；
                                //人话：无论你调用代理对象的什么方法，它都是在调用 InvocationHandler实现类的实例对象h 的invoke()方法！
        )


        *  */


        //返回值是 Object类型的；真正使用时要 向下转型为自己需要的类型嗷(如 接口类型)！
        /*  public static Object newProxyInstance的三个参数：【简单版】
        * 1.类加载器：真实对象.getClass().getClassLoader()
        *
        * 2.接口数组：
        * -在动态代理中：代理对象和真实对象实现相同的接口！！所以接口数组必需传入 【真实对象A已实现的接口】
        *  ▲若需传入的接口是 【真实对象A已实现的接口】，可以直接从此对象A中拿到这个Class<?>[]类型的接口数组
        *    - 真实对象.getClass().getInterfaces()
        *    - Class[]<?> getInterfaces() --【Class类_类型的数组，带泛型<?>哦！！】数组元素是 【要实现的接口】的 class类_类型 实例对象
        *      ▲ getInterfaces()只返回当前类直接实现的接口，并不包括其父类实现的接口
        *
        * 3.处理器：new InvocationHandler()
        * */

        //获取真实对象的类加载器
        ClassLoader cld = lenovo.getClass().getClassLoader();

        //----------Proxy.newProxyInstance( 类加载器，接口数组，处理器 )
        /* 传入接口数组时：已知接口类名时，直接使用： 接口的类名.class 的写法
        * 在动态代理中：代理对象和真实对象实现相同的接口！！所以接口数组必需传入 【真实对象A已实现的接口】
        *   若需传入的接口是 【真实对象A已实现的接口】，可以直接从此对象A中拿到这个Class<?>[]类型的接口数组
        * */

        //传入接口数组时：已知(真实对象已实现的)接口类名时，直接使用： 接口的类名.class 的写法
        Class<?>[] cls_interfaces  = new Class<?>[]{ SaleComputer.class }; //真实对象已实现的接口类名为SaleComputer
        //在动态代理中：代理对象和真实对象实现相同的接口！！所以接口数组必需传入 【真实对象A已实现的接口】
        //若需传入的接口是 【真实对象A已实现的接口】，可以直接从此对象A中拿到这个Class<?>[]类型的接口数组
        Class<?>[] cls_interfaces2 = lenovo.getClass().getInterfaces();

        /* 为什么 Proxy.newProxyInstance 返回值 Object，
        * 可以向下转型为 【此动态代理类实例对象中 已实现的任意一个接口】 类型的对象？
        *
        * 原因就是在 newProxyInstance 这个方法的第二个参数上：
        * 我们给这个代理对象提供了一组接口 interfaces = { 接口1, 接口2, 接口3, ... ,接口n }
        * 那么我这个代理对象就会实现了上面这组接口 interfaces；
        * 这个时候我们当然可以将这个代理对象强制类型转化为这组接口interfaces中的【任意一个】！！
        *
        * 因为这里传入的接口是 SaleComputer 类型的，所以就可以将其向下转型为 SaleComputer 类型了。
        *  */

        //返回值原本是Object类型的，真正调用时需要 向下转型为 【此动态代理类实例对象中 已实现的任意一个接口】 类型的对象
        SaleComputer proxy_lenovo = (SaleComputer)Proxy.newProxyInstance(
                cld, //真实对象的类加载器
                cls_interfaces, //接口数组

                new InvocationHandler(){  //处理器。匿名内部类的写法！
                    @Override  //使用IDEA快速帮我生成需重写的方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /* invoke方法的3个参数
                        1.proxy动态代理类的实例对象，通常情况下不需要它。
                          但可以使用 getClass () 方法，得到 proxy 的 Class 类从而取得实例的类信息，如方法列表，annotation 等。

                        2.method方法对象：代理对象调用的方法，被封装为的对象
                         代表被动态代理类调用的方法。从中可得到方法名，参数类型，返回类型等等

                        3.args对象数组：代理对象调用的方法时，传递的实际参数
                         代表被调用方法的参数。注意基本类型 (int,long) 会被装箱成对象类型 (Interger, Long)
                        * */


                        return null;
                    }
                }


        );






        //----------3.使用代理对象调用方法
        //因为代理对象与真实对象实现了相同的接口，所以代理对象与真实对象有相同的方法可调用
    }




}
