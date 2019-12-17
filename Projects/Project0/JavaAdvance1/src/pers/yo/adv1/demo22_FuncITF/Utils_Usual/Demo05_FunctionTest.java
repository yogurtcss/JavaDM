package pers.yo.adv1.demo22_FuncITF.Utils_Usual;

/*
    练习：自定义函数模型拼接
    题目
    请使用Function进行函数模型的拼接，按照顺序需要执行的多个函数操作为：
        String str = "赵丽颖,20";

    分析:
    1. 将字符串In截取数字年龄部分，得到字符串Out；
         "赵丽颖,20"->"20"
    2. 将上一步的字符串In转换成为int类型Out的数字；
         "20"->20
    3. 将上一步的int数字In累加100，得到结果int类型Out数字。
         20->120
 */
import java.util.function.Function;

public class Demo05_FunctionTest {
    public static void method( String str,  Function<String,String> func1,
                               Function<String,Integer> func2,  Function<Integer,Integer> func3 ){
        System.out.println( func1.andThen(func2).andThen(func3).apply(str) );
    }

    public static void main(String[] args) {
        method(
                "赵丽颖,20",
                s -> s.split(",")[1],  //"赵丽颖,20"->"20"
                s -> Integer.parseInt(s),    //"20"->20
                i -> i+100                  //20->120
        );
        //基本数据类型转字符串型，推荐使用String的静态方法：String.valueOf(基本数据类型)
    }
}
