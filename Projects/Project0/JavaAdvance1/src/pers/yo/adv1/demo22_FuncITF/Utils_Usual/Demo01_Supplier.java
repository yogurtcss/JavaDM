package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/* 常用的函数式接口

Supplier 供应商、提供者

Supplier 其实表达的不是从一个参数空间到结果空间的映射能力，而是表达一种生成能力，
因为我们常见的场景中不止是要 consume（Consumer）或者是简单的 map（Function），
还包括了 new 这个动作。而 Supplier 就表达了这种能力。

java.util.function.Supplier<T> 接口仅包含一个无参的方法：T get();
   ▲  T get() 获取(返回)一个 指定泛型类型的【实例对象】(或 基本数据类型的值)

Supplier<T>接口被称为“生产型接口”：
若指定此接口的泛型是XXX类型，
则接口中的get方法就会 生产(返回)【对应的】XXX类型的数据——实例对象 或基本数据类型的值

*  */

import java.util.function.Supplier;

public class Demo01_Supplier {
    /* 定义一个方法，向方法传形参为 【函数式接口】Supplier<T>接口；
    * 指定接口的泛型为 【String】；
    * 那么get方法就会返回一个String
    *  */
    public static String getString( Supplier<String> sup ){ //若方法的形参为函数式接口，则可使用lambda表达式
        return( sup.get() );
    }

    public static void main(String[] args) {
        String s1 = getString( new Supplier<String>(){ //匿名内部类作为实现类，以创建实现类的实例对象
            public String get(){
                return "匿名内部类！";
            }
        } );
        System.out.println( s1 );

        String s2 = getString( ()->"Lambda表达式！" );
        System.out.println( s2 );

    }
}
