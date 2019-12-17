package pers.yo.adv1.demo11_API2;

/* java.lang.StringBuilder类：
* 字符串缓冲区：可以提高字符串的操作效率(看成一个长度可以变化的字符串)
* 底层也是一个数组，但没有被final修饰，可以改变长度
*
* 构造方法 ——即把String转为StringBuilder
*   StringBuilder() 构造一个不带任何字符的字符串生成器，其初始容量为16个字符
*   StringBuilder( String str ) 构造一个字符串生成器，并初始化为指定的字符串内容
*
* 而把StringBuilder转为String：
*   StringBuilder实例对象.toString()，返回此StringBuilder实例对象的String类型
* 使用：  String s = (StringBuilder实例对象bu) .toString();
*
* 常用方法
*   StringBuilder实例对象.append(str)，添加任意类型数据的字符串形式，并返回当前对象自身(无需手动接收返回值)，类似于python中list.append(..)
*
* 链式编程：若方法的返回值是一个对象，可以继续调用此对象中的方法(疯狂地点点点...调用方法)
*
*
*  */


public class Demo05StringBuilder {
    public static void main(String[] args) {
        StringBuilder bu = new StringBuilder();
        bu.append( "嘻嘻嘻" );
        System.out.println( bu );

        String s = bu.toString();
        System.out.println( s );

    }
}
