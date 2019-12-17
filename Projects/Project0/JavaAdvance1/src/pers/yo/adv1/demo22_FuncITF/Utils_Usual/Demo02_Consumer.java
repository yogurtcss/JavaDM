package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
    java.util.function.Consumer<T>接口则正好与Supplier接口相反，
        它不是生产一个数据，而是消费一个数据，其数据类型由泛型决定。
    Consumer接口中包含抽象方法void accept(T t)，意为消费一个指定泛型的数据。

   Consumer接口是一个消费型接口,泛型执行什么类型,就可以使用accept方法消费什么类型的数据
   至于具体怎么消费(使用),需要自定义(输出,计算....)
 */
import java.util.function.Consumer;

public class Demo02_Consumer {
    public static void consumeStr( String oneString, Consumer<String> con ){
        con.accept( oneString );
    }

    public static void main(String[] args) {
        String oneString = "consumer接口！";
        consumeStr(
                oneString,
                //消费方式，直接输出
                //(s)-> System.out.println(s)

                /* 消费方式：反转输出
                * public StringBuffer reverse()
                * 此方法返回具有相反顺序的 StringBuffer 对象。
                *
                * new StringBuffer(s).reverse().toString() 是反转后的字符串，我懒得使用String变量保存结果了
                *  */
                (s)-> System.out.println( new StringBuffer(s).reverse().toString() )
        );
    }
}
