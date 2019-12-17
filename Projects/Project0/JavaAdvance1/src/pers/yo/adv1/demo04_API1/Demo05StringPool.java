package pers.yo.adv1.demo04_API1;

/* 字符串常量池(位于堆中)：
* 程序中所有直接写出的(即没有被new出来的) 双引号字符串，就在字符串常量池中
* 正因为字符串永不可变，所以字符串是可以共享使用的(帮助理解 字符串常量池)
*
* 对于基本数据类型来说，==进行数值的比较
* 对于引用类型来说，==进行【地址值】的比较
*  */

public class Demo05StringPool {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";

        char[] charArr = new char[]{'a','b','c'};
        String str3 = new String( charArr ); //传入 {'a','b','c'}的字符数组嗷

        System.out.println( str1==str2 ); //true
        System.out.println( str1==str3 ); //false
        System.out.println( str2==str3 ); //false
    }
}
