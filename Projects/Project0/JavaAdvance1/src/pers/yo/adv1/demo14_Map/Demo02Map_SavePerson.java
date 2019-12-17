package pers.yo.adv1.demo14_Map;

/* HashMap存储自定义类型的键值对
*
* 集合HashMap<K,V>，底层是哈希表
* 基于哈希函数来存储键值对
* 为保证键key的唯一性：键key所属的类型 需要必须重写 hashCode()方法，和equals()方法
*
*  */

import java.util.HashMap;


import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import pers.yo.adv1.demo14_Map.Person;

public class Demo02Map_SavePerson {
    public static void main(String[] args) {

        /* Person类中：String类型name，int型age
        * 使用HashMap存储Person类中的数据时：
        * 【例1】以String类型name作为“键”，以 Person类的实例对象p 作为“值”
        *
        * 分析此时的键值对情况：(key键是否已重写了hashCode()方法，和equals()方法？)
        * key为String类型；已知事实是 String类已重写了hashCode()方法，和equals()方法，可保证key键唯一
        * value为Person类型，可以重复的
        *  */

        /* 关于HashMap<K,V>实例对象的创建
        * 普通的创建格式(没有 上转型)：HashMap<String,String> map = new HashMap<>();
        * 此map若要使用Map接口中的公共方法时，可通过 上转型 “多态写法”创建：Map<String,String> map = new HashMap<>();
        *  */
        HashMap<String,Person> map1 = new HashMap<>();
        map1.put( "A1", new Person("北洛",23) );
        map1.put( "A2", new Person("云无月",22) );
        map1.put( "A3", new Person("小缨子",18) );
        //先取出所有的键(keySet方法)构成一个集合keys，然后使用增强for循环 遍历此keys，取各键对应的值
        Set<String> keys = map1.keySet();
        for( String oneKey: keys ){
            System.out.println( oneKey+"："+map1.get(oneKey) ); //map.get(oneKey)获取此键对应的值
        }
        System.out.println( "----------------------------------" );

        /* Person类中：String类型name，int型age
        * 使用HashMap存储Person类中的数据时：
        * 【例2】以 Person类的实例对象p 作为“键”，以String类型name作为“值”，【与例1颠倒一哈】
        *
        * 分析此时的键值对情况：(key键是否已重写了hashCode()方法，和equals()方法？)
        * key为Person类型；此自定义的Person类型必须重写hashCode()方法，和equals()方法，以保证key键唯一！！
        *      怎么重写？(1)手动重写；(2)使用alt+insert键，快速重写这俩方法…
        * value为String类型，可以重复的
        *  */
        HashMap<Person,String> map2 = new HashMap<>();
        map2.put( new Person("柿饼",9), "A4" );
        map2.put( new Person("玄戈",100), "A5" );
        map2.put( new Person("柿饼",9), "A6" );
        /* Person类型未重写hashCode()方法，和equals()方法时，map集合中会存在两个“柿饼”：A4号柿饼 和A6号柿饼
        * 当Person类型重写了hashCode()方法，和equals()方法后，后来的柿饼将替换掉前一个柿饼，map集合中只剩下一个“柿饼”：A6号柿饼
        *  */
        Set< Map.Entry<Person,String> > kv = map2.entrySet(); //取出所有键值对，构成一个集合kv
        System.out.println( "迭代器遍历 Set< Map.Entry<Person,String> > kv 键值对集合！！！" );
        Iterator< Map.Entry<Person,String> > itKV = kv.iterator(); //在此集合kv上建立一个迭代器
        while( itKV.hasNext() ){
            System.out.println( itKV.next() ); //直接输出下一个键值对
        }
        System.out.println();//换行
        System.out.println( "------------以下使用keySet()、增强for循环或迭代器迭代，来辣！" );

        Set<Person> keys2 = map2.keySet(); //取出所有键，构成一个集合kv，以下用增强for循环或迭代器，均可
        System.out.println("增强for循环keySet() 所有键的集合！！");
        for( Person oneKey: keys2 ){
            System.out.println( oneKey+"："+map2.get(oneKey) );
        }
        System.out.println( "迭代器遍历keySet() 所有键的集合！" );
        Iterator<Person> itP = keys2.iterator();
        while( itP.hasNext() ){ //迭代器中不能用“一步到位”的跳步写法
            //迭代器中，正确写法：先用next()方法取出“下一个键”，暂存起来，然后在到map2中索引到 对应的值
            Person oneKey = itP.next();
            System.out.println( oneKey+"："+map2.get(oneKey) );

            // 错误写法：一步到位。不能一步到位！！
//            System.out.println( itP.next()+"："+map2.get(itP.next()) );

            /* 这里 前面第一个的itP.next()是取“下一个值”(如 A)，
            * 而在后一个 map2.get(itP.next())的 itP.next()是取“下下一个值”(即 A的下一个！！) 【默认再一次调用next()方法时，是该元素的下一个元素】
            *
            * 这样，当到达最后一个元素时：前面第一个的itP.next()取的是“最后一个元素”，(到底了，下面已经不能再用next方法了！！)
            * 而在后一个 map2.get(itP.next())的 itP.next()取的是“最后一个元素的 下一个元素——是什么？？null”，
            * 这样就在map集合中索引不到 这 “最后一个元素的 下一个元素” 从而报错喽
            * 所以，迭代器中，不要用“一步到位”的跳步写法！！
            *  */
        }




    }
}
