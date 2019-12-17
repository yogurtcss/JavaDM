package pers.yo.adv1.demo04_API1;

/* 程序中所有的双引号字符串，(就算它没有被new出来)都是String类的实例对象。
* 如"abc"，它没有被new出来，它也是String类的实例对象，
* 实例对象"abc"可以调用String中的方法嗷："abc".XXX方法
*
* 字符串是常量，它是永不可变的；
* 正因为字符串永不可变，所以字符串是可以共享使用的(帮助理解 字符串常量池)
* 字符串相当于 char[] 字符数组，但字符串的底层原理是：用byte[]字节数组(依据ASC II表)存储 代表 原本每一个字符的 字节数据
*
* 所以字符串的构造方法与字符数组有py交易嗷：
*
* String的构造方法——在文件输入字节流中，可呈现——Demo02InputStream1.java
*  1.String( byte[] bytes ) 将字节数组转换为字符串
*  2.String( byte[] bytes, int offset, int length )
*    把字节数组的一部分转换为字符串，offset为字符数组的开始索引，length转换的个数
*
*
* 创建字符串的常见3+1种方式
* 3种构造方法：
* (1)public String()：创建一个空白字符串，没有任何内容
* (2)public String( char[] array )：根据 char字符数组的内容 来创建对应的字符串 ——可用于 将字符数组转换为字符串嗷
* (3)public String( byte[] bytes )：根据 byte字节数组的内容 来创建对应的字符串 ——可用于 将字节数组转换为字符串嗷
*
* 1种 直接创建 String str1 = "hahaha";
* 开局第一句话(最上面)：程序中所有的双引号字符串，(就算它没有被new出来)都是String类的实例对象。
*  */


public class Demo05String {
    public static void main(String[] args) {
        char[] charArr = new char[]{ 'a','b','c' };
        String str1 = new String( charArr );
        System.out.println( str1 );

        byte[] byteArr = new byte[]{ 98,99,100 };
        String str2 = new String( byteArr );
        System.out.println( str2 );
    }
}
