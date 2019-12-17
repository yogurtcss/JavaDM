package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
    java.util.function.Function<In,Out>接口：根据一个类型In的数据得到另一个类型Out的数据，
        前者In称为前置条件，后者Out称为后置条件。
    Function接口中最主要的抽象方法为：R apply(T t)，根据类型T的参数获取类型R的结果。
        使用的场景例如：将String类型转换为Integer类型。

-----------------------------------------------------

Function接口中的默认方法andThen：
用来组合 【两个Function函数式接口func1、func2】的 操作

//返回一个【先执行当前函数对象apply方法，再执行after函数对象apply方法】的函数对象。
default <V> Function<In, Out> andThen(Function<? super R, ? extends Out> after) {
        Objects.requireNonNull(after);
        return (In in) -> after.apply(apply(in));
}

▲ 注意，此Function接口的默认方法andThen()是有返回值的：返回值为 Function<T, V>类型

func1.andThen(func2).apply()
表示先做func1，然后【在func1完成的结果上】紧接着做func2

-----------------------------------------------------
注：Function接口的andThen() 与 Consumer接口的andThen() 区别

含义不同！！

▲ Function接口的andThen()，用法为 func1.andThen(func2).apply() ——因为Function接口的默认方法andThen()是有返回值的
表示先做func1，然后【在func1完成的结果上】紧接着做func2；
【两个func1、func2的结果是有关联的】


▲ Consumer接口的andThen()，用法为 con1.andThen(con2).accept(s); 谁写前边谁先消费
连接两个Consumer接口  再进行消费；
因为没有返回值，所以：当多个Consumer接口实现类的实例对象con1、con2先后消费同一变量时，
【con1、con2消费的结果 互相独立、互不影响】

 */

import java.util.function.Function;

public class Demo05_Function {
    // string to int
    public static void str2int( String str, Function<String,Integer> func ){
        System.out.println( func.apply(str)+10 );
    }
    /* 把String类型的"123",转换为Integer类型,把转换后的结果加10
    * 把增加之后的Integer类型的数据,转换为String类型
    *  */
    public static void parseInt2str( String str, Function<String,Integer> func1, Function<Integer,String> func2 ){
        System.out.println( func1.andThen(func2).apply(str) );
    }


    public static void main(String[] args) {
        str2int(
                "10",
                s -> Integer.parseInt(s)+10
        );

        parseInt2str(
                "123",
                s -> Integer.parseInt(s)+10,
                i -> String.valueOf(i) //或者最简单的方法 i+"" 空字符串
                //注意：执行的逻辑是 先做func1，然后【在func1完成的结果上】紧接着做func2；
        );

        /*
          【推荐使用】String.valueOf。这个方法在使用的时候是有些特殊的。
           一般情况下，如果是确定类型的 null传入(如某实例对象的值为null)，则返回的是字符串 “null”，
           而如果直接传入 null，则会发生错误。

           String.valueOf 这个方法是静态的，直接通过 String 调用，可以说是完美，只是平时不习惯这样写而已。
           在方法valueof的内部做了“是否为空”的判断，所以就不会报出空指针异常。源码如下：
           public static String valueOf(Object obj){
              return (obj==null) ? "null" : obj.toString()
              //注意看三目运算符处：当object为null时，String.valueOf（object）的值是字符串“null”，而不是 null！！！
           };

           但是，这也恰恰给了我们隐患。我们应当注意到，
           当object为null时，String.valueOf（object）的值是字符串“null”，而不是 null！！！
           在使用过程中切记要注意。

       * */
        Object obj_Null = null;
        System.out.println( String.valueOf(obj_Null) ); //字符串的null

    }
}




