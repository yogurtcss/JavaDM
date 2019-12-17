package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/* 【老番新看环节】
lambda表达式的 格式(类似 箭头函数的格式) 两种固定格式：
( 参数类型 参数名1, 参数类型 参数名2, ...,  ) ->  单个表达式expression;  //单个表达式，不用花括号

( 参数类型 参数名1, 参数类型 参数名2, ...,  ) ->  { 代码块：函数式接口抽象方法的实现逻辑 };
//一般的代码块，需要花括号，里面手动return返回值；

*  */


/*  Predicate 断言

    java.util.function.Predicate<T>接口
    作用:对某种数据类型的数据进行判断,结果返回一个boolean值

    Predicate接口中包含(仅有的)一个抽象方法：
        boolean test(T t):用来对指定数据类型数据进行【判断】的方法
            结果:
                符合条件,返回true
                不符合条件,返回false

    ----------------------------------------------
    Predicate接口中的3个默认方法 and(且)、or(或)、negate(非)
    注意，and、or方法只是“条件连接”作用；negate方法只是“条件取反”的作用
    这3个方法的后面，都要 紧接着调用.test()，才会进行条件的判断嗷！

    1.and()
    将 两个predicate函数式接口pre1、pre2表示的条件 作 “且”的连接，
    表示：当 【既满足pre1的条件，又要满足pre2的条件】时，才返回true； 否则返回false

    2.or()
    将 两个predicate函数式接口pre1、pre2表示的条件 作 “或”的连接，
    表示：当 【满足pre1的条件，或 满足pre2的条件 或 全满足pre1、pre2的条件】时，才返回true； 否则返回false

    3.negate()
    将 某个predicate函数式接口pre1表示的条件，“取反”

*/

import java.util.function.Predicate;

public class Demo03_Predicate {
    public static boolean checkString( String s, Predicate<String> pre ){
        return( pre.test(s) ); //抽象方法test
    }

    public static void checkAnd( String s, Predicate<String> pre1, Predicate<String> pre2 ){
        boolean isValid = pre1.and(pre2).test(s);
        System.out.println( "字符串符合要求吗？"+isValid );
    }

    public static void checkOr( String s, Predicate<String> pre1, Predicate<String> pre2 ){
        boolean isValid = pre1.or(pre2).test(s);
        System.out.println( "字符串符合要求吗？"+isValid );
    }

    public static void checkNegate( String s, Predicate<String> pre1 ){
        boolean isValid = pre1.negate().test(s);
        System.out.println( "字符串符合要求吗？"+isValid );
    }


    public static void main(String[] args) {
        //对参数传递的字符串进行判断,判断字符串的长度是否大于5,并把判断的结果返回
        boolean b = checkString(
                "hello",
                s -> s.length()>5    //写：判断的条件
        );
        System.out.println( "符合条件吗？"+b );
        System.out.println( "-------------------" );
        //判断字符串的长度是否大于5 且 是否是否包含a
        checkAnd(
                "HelloWorld",
                s -> s.length()>5,
                s -> s.contains("a")
        );
        System.out.println( "-------------------" );
        //判断字符串的长度是否大于5 或 是否是否包含a
        checkOr(
                "Hellooooooooo",
                s -> s.length()>5,
                s -> s.contains("a")
        );
        System.out.println( "-------------------" );
        /* 判断字符串的长度是否大于5；
        * 若大于5本应返回true，我要你取反输出为false！！
        * 若小于5本应返回false，我要你取反输出为true！！
        *
        * 在Predicate接口中调用 pre1.negate().test()就是“取反”了，就完事了嗷
        *  */
        checkNegate(
                "Hellooooooooo", //本应返回true的
                s -> s.length()>5
        );
    }
}
