package pers.yo.adv1.demo12_Collection;

/* Collection接口，中定义了获取集合类迭代器的方法：实例coll. iterator()
*
* java.util.Iterator接口：迭代器，用于遍历集合
* Iterator迭代器是一个接口，无法直接new出来使用；(因为接口需要实现类进行实现，然后创建实现类的实例对象进行使用)
*
* 假设已有  Collection类的某个实例对象coll，
* 欲创建一个 迭代coll集合元素 的迭代器，需使用此语句：
*
* Iterator<与集合相同的泛型> 迭代器名 = 实例coll .iterator(); //多态，左父(Iterator接口)右子(实例对象)
* 返回一个 在 此coll表示的集合上 进行迭代 的【迭代器】——即 获取迭代器的 实现类的实例对象
*
* 常用的方法
*     hasNext() 判断集合中有没有下一个元素，有则返回true，否则false
*     next() 返回迭代的下一个元素，返回值类型为【Object型】！！
*        问：为什么next方法的返回类型是Object的呢？
         答：为了可以接收任意类型的对象。那么返回的时候，不知道是什么类型的就定义为 object型

*
*
*  */

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class Demo02Iterator {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>(); //多态
        coll.add( "啊啊" );
        coll.add( "孙笑川258" );
        coll.add( "带带大师兄" );

        //根据语法格式，创建一个在 coll表示的集合上 进行迭代的迭代器(获取一个迭代器的实现类的实例对象)
        Iterator<String> it = coll.iterator(); //此时指针指向 coll表示的集合 的-1索引上
        /* 当调用了 it.next()时，做了两件事：
        * 1.取出下一个元素
        * 2.会把指针后移一位
        *  */
        while( it.hasNext() ){
            //迭代器对象的next() 返回的是Object类型，记得要 强制类型转换
            System.out.println( (String)it.next() );
        }
        System.out.println( "1------------------" );

        /* for循环写法，仍按照for的格式
        * for( 初始化表达式; 循环条件; 步进表达式-递增/减 ){ ... }
        * 初始表达式为：获取一个迭代器
        * 循环条件为：是否存在下一个元素 it.hasNext()
        * 步进表达式：省略不写！！！
        * 因为for循环体中的 it.next()方法既取出元素，又把指针后移一位(默认i++)，所以步进表达式省略不写了
        *  */
        for( Iterator<String> it2 = coll.iterator(); it2.hasNext(); ){ //步进表达式省略不写了
            System.out.println( (String)it2.next() );//因为for循环体中的 it.next()方法既取出元素，又把指针后移一位，所以步进表达式省略不写了
        }

        /* 增强型for循环
        * for( 待遍历元素的数据类型 变量名currValue : 待遍历的Collection集合实例对象coll 或数组 ) {
        *     //...
        * }
        *  */
        System.out.println( "2------------------" );
        for( String s : coll ){ //增强型for循环，但丢失了下标信息
            System.out.println( s );
        }


    }
}
