package pers.yo.adv1.demo12_Collection;

/* 定义含有泛型的方法：泛型定义在方法的 【修饰符】与【返回值类型】 之间！！格式如下
* 修饰符 <泛型M> 返回值类型 方法名( 泛型M 形参m1， 泛型M 形参m2， ... ){
*     //...
* }
*
* 含有泛型的方法method，在它被调用时，确定此泛型的数据类型：
* 传入什么类型的数据，泛型就是什么类型
*  */

public class GenericMethod {
    //定义含有泛型的方法：泛型定义在方法的 【修饰符】与【返回值类型】 之间！！
    public <M> void  method( M m ){
        System.out.println( "我是泛型！！ "+m );
    }
}
