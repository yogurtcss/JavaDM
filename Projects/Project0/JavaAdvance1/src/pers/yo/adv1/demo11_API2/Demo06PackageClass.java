package pers.yo.adv1.demo11_API2;

/* 包装类：Package Class
* 若想使基本数据类型 像 对象一样操作，可以使用基本数据类型对应的 包装类
*
* 基本数据类型与其对应的包装类对象之间 “来回转换”的过程，称为 装箱、拆箱
*
* 装箱：从 基本数据类型 转换为 对应的包装类对象
* 拆箱：从 包装类对象   转换为 对应的基本数据类型
*
* 从 JDK1.5 开始，系统完成 自动装箱与自动拆箱
*

*  */

public class Demo06PackageClass {
    public static void main(String[] args) {
        Integer i = new Integer( 4 ); //装箱
        int num = i.intValue(); //拆箱
        Integer in = 1; //自动装箱，相当于 Integer in = new Inteter(1)

    }
}
