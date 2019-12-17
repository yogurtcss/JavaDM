package pers.yo.adv1.demo12_Collection;

/*  Java 中的集合框架大类可分为：
* 单列集合 java.util.Collection 和 双列集合 java.util.Map

注意：
1.遍历Collection接口下的 (实现类)的集合时：
只能使用 增强型for循环 或 迭代器迭代 来遍历！！

2.遍历Map接口下的 (实现类)的集合map时：
因为map中的数据是 键值对 形式的，所以：
使用 map.keySet() 取出所有的键并存在一个集合Set中
然后遍历此集合Set：
* 因为Set是属于Collection接口的实现类，所以遍历Set时可用 增强型for循环 或 迭代器迭代 来遍历！！



Collection：单列集合类的根接口，用于存储一系列符合某种规则的元素。
它有两个重要的子接口，分别是 java.util.List 和 java.util.Set。其中：

感觉List和Set是 死对头
List 的特点是【元素有序】、【元素可重复】。
Set 的特点是【元素无序】，【而且元素不可重复】。

List 接口的主要实现类有：
1.java.util.ArrayList
2.java.util.LinkedList  //包含了许多操作首位元素的方法：增删改查
linkedList底层是一个链表结构，查询慢，增删快

Vector集合已过时，废弃了。

List 集合中特有的方法有
1.增：  void add (int index, E泛型 element) ：将指定的元素，【添加】到该集合中的指定位置上
2.删：  public E remove( int index ) ：【移除】列表中指定位置的元素，并返回此被移除的元素
3.改：  public E set( int index, E new )：【修改】用指定元素new 替换 原集合中指定下标index的元素，返回【更新前的元素】
2.public E get (int index)： 返回集合中指定位置的元素 【常用！！List.get(下标) ——斗地主综合案例】


Set 接口【元素无序】，【而且元素不可重复】，其主要实现类有：
主要共有的方法：增删改查
1.java.util.HashSet
▲ 遍历 HashSet的方法：不能用普通for循环，只能用增强型for循环、或 迭代器iterator 遍历！
使用迭代器遍历的方法：
(1)根据iterator()获取 在此HashSet类实例对象上 的迭代器
(2)遍历迭代器获取各个元素 while( it.hasNext() ) {....}

//哈希值：一个十进制整数，由系统随机给出(实例对象的逻辑地址值，不是实际存储的物理地址)

2.java.util.TreeSet


----------------------------------------------------------------------------
Collection 常用功能
Collection 是所有单列集合的父接口，因此在 Collection 中定义了单列集合（List 和 Set）通用的一些方法，这些方法可用于操作所有的单列集合。

方法如下：

public boolean add(E e):把给定的对象添加到当前集合中。
public void clear():清空集合中所有的元素，集合仍存在
public boolean remove(E e):把给定的对象在当前集合中删除。
public boolean contains(E e):判断当前集合中是否包含给定的对象。
public boolean isEmpty():判断当前集合是否为空。
public int size():返回集合中元素的个数。
public Object[] toArray():把集合中的元素，存储到数组中。


------------------------------------------
可变参数 ... 三点(传入方法中的多个参数)
定义一个方法时，需传入 多个【数据类型相同】的参数，则可用...三点 的格式：
修饰符 返回值类型 方法名( 参数类型... 形参名 ){
    //...
}

▲ 可变参数的原理：其底层是一个数组，根据传递参数个数不同，会创建长度不同的数组来存储这些参数

注：1.传递的参数个数可以是0个(即不传递)、1、2、3……多个。
2.一个方法的参数列表，【至多】只能有一个可变参数
3.若方法中【不同类型】的参数有多种多个，则 某类型的可变参数 必须写在 参数列表的最末尾
如 public void method( String s, double d, char c,   int...a ){...}
  出现了多种不同类型(String，double，char，int)的参数！！则此int类型的可变参数 必须写在 参数列表的最末尾


------------------------------------------
java.utils.Collections 是集合工具类，用来对集合进行操作 【里面全是静态方法】
注意，此Collections是 首字母大写、复数！！

Collections.addAll( 集合A,   指定的集合元素a,b,c.. ) ：向集合A中添加指定的集合元素a,b,c...
Collections.shuffle( 集合A ) ：将原集合中的元素 按默认的随机规则，进行随机排序 //斗地主
Collections.sort( 集合A )： 将集合A中的元素按默认规则排序


* */


import java.util.Collection;
import java.util.ArrayList;

public class Demo01Collection {
    public static void main(String[] args) {
        /* 集合Collection是接口，不能直接new出来使用，
        * 需要 此接口的 实现类 的实例对象
        * ArrayList上转型即可
        *  */
        Collection<String> coll = new ArrayList<>(); //多态
        coll.add( "孙笑川258" );
        coll.add( "带带大师兄" );

        coll.remove( "孙笑川258" );
        System.out.println( coll );
        System.out.println( coll.contains("孙笑川") );
    }
}
