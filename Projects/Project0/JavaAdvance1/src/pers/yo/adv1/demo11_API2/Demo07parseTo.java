package pers.yo.adv1.demo11_API2;

/* 基本数据类型与字符串之间的转换
*
基本类型 -> String
1.基本类型的值 + "" (最简单的方法)
2.(String) 字符串类型强转。需要保证的是类型可以转成 String 类型。
3.包装类的静态方法toString(参数)方法
【前提条件】：需要保证调用这个方法的类、方法、变量不为 null，否则会报空指针异常。
如Integer.toString( int型数值 )，返回字符串型
4.【推荐使用】String.valueOf。这个方法在使用的时候是有些特殊的。
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



*
* 3.String类的静态方法 valueOf()
*
* String -> 基本类型
*
* 使用包装类的静态方法.parseXXX("字符串")
* Integer.parseInt("字符串")，返回 int 型
* Double.parseDouble("字符串")，返回 double 型
*  */



public class Demo07parseTo {
    public static void main(String[] args) {
        String s = "1";
        int i = Integer.parseInt(s);
    }
}
