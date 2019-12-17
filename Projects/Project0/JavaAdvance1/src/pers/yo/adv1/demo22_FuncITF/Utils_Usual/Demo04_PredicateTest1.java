package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
    练习：集合信息筛选
    数组当中有多条“姓名+性别”的信息如下，
    String[] array = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女" };
    请通过Predicate接口的拼装将符合要求的字符串筛选到集合ArrayList中，
    需要同时满足两个条件：
        1. 必须为女生；
        2. 姓名为4个字。
 */

import java.util.ArrayList;
import java.util.function.Predicate;

public class Demo04_PredicateTest1 {
    //函数时接口作为方法的参数
    public static void check( String[] arr, ArrayList<String> rst, Predicate<String> pre1, Predicate<String> pre2 ){
        for( String one : arr ){
            boolean preRst = pre1.and(pre2).test(one);
            if( preRst==true ){//条件可以缩写为 if( preRst )
                rst.add( one );
            };
        };
    };

    public static void main(String[] args) {
        String[] arr = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女" };
        ArrayList<String> rst = new ArrayList<>();
        check(
                arr,
                rst,
                //
                s -> s.split(",")[0].length()==4,   //基本数据类型，使用==号比较
                s -> s.split(",")[1].equals("女")   //不能用==号比较；对于String类型的数据，只能用.equals()方法
        );
        for( String one : rst ){
            System.out.println( one );
        }
    }
}

/* 什么时候应该用 equals（），什么时候应该用 == ？

1、equals 是 object 的方法

2、String 类型的
equals 重写了 object 的方法，所以此方法比较的是内容，不比较内存地址
== 比较内容和地址，因为 String 也属于引用数据类型
注意：String 是特殊的在声明对象时可以写 new 也可以不写 (不写默认就是 new)
（总结：== 比较内存地址和内容，equals 方法被重写只比较内容）

3、基本类型，如 int,char,long,boolean。
没有 equals 方法，只有 == 只比较值，因为基本数据类型存在栈里也不能 new，最关键的一点是只有对象才能调方法
（总结：基本数据类型只有 == 进行比较，只比较值）

4、引用数据类型，如 Integer,Byte,Long,Character,Boolean
引用数据类型是可以 new 的，而 new 出来的对象都会在堆中有开辟一个内存地址空间
通常用 == 比较对象时，比较的就是内存地址和内容
equals 是用 == 判断两个对象是否相等，比较内存地址和内容，当两者都相等时才返回真
（总结：引用数据类型除 String 特殊外，equals 和 == 都比较内存地址和内容）


* */

