package pers.yo.adv1.demo04_API1;

/* 对象数组：数组元素是对象的数组
*  */

import java.util.ArrayList;
/* 数组的长度是不可变的，但ArrayList集合的长度是可以任意变化的
*
* 对于ArrayList来说，它(旁边)有一个尖括号<E> 表示泛型
* 泛型：此集合中的所有元素，全是统一的XX引用类型；
* 注意，泛型只能是引用类型，不能是 基本数据类型！！
*
* 若希望向ArrayList中存储 基本数据类型的数据，
* 则此时的泛型必须为 基本数据类型所对应的“包装类”
*  */


public class Demo04ArrayList {
    public static void main(String[] args) {
        //创建一个ArrayList集合，集合名为list，里面的元素全是String类型的
        ArrayList<String> list = new ArrayList<>(); //右侧的一对尖括号<>必须要写！！尖括号里可以不写内容
        /* 若直接打印一个 ArrayList集合，则会显示内容[...]，而不是地址值
        *  */
        System.out.println( list );
        list.add( "我佛了" ); //类似python的 列表.append
        System.out.println( list );

        /* 假设已经有实例化对象list：
        * ArrayList<String> list = new ArrayList<>()
        * 常用的方法
        * list.get(集合中的下标)，获取某个元素
        * list.remove(下标)，删除某元素
        * list.size()，无形参，获取集合的长度(即集合中的元素个数)
        *  */

        /* 若希望向ArrayList中存储 基本数据类型的数据，
        * 则此时的泛型必须为 基本数据类型所对应的“包装类”
        *
        * 记住这两个特殊的：int - 包装类为Integer
        * char - 包装类为Character
        * 其余的包装类，均为 原基本数据类型首字母大写嗷
        *  */
        ArrayList<Integer> listInt = new ArrayList<>();
        listInt.add(233);
        listInt.add(100);
        System.out.println( listInt );

    }
}
