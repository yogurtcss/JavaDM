package pers.yo.adv1.demo14_Map;

/* Map接口 将键映射到值的接口，每个键key最多只能映射一个值value
* key是唯一标识性的，故不可重复；而value可重复
* key和value的数据类型是任意的，两者数据类型可相同也可不同
* 普通的创建格式(没有 上转型)：HashMap<String,String> map = new HashMap<>();
*
* ---------------------------
* Map接口中的常用方法：增删、查
*
* Map常用子类：先向上转型为Map接口的对象，才可调用Map接口中的常用方法
* 此map若要使用Map接口中的公共方法时，可通过 上转型 “多态写法”创建 ：Map<String, String> map = new HashMap<>();
注：以下所用的map为 向上转型为Map接口的实例对象

* 增：map.put( K类型 key，  V类型 value )： 将 给定的键、值，以【键-值对】的形式(即映射完毕后)，添加到Map集合中。默认返回的是 此key对应的前一个值value
     调用put方法时，如果已经存在一个相同的key，则返回的是前一个key对应的value， 同时 该key的新value覆盖旧value；
     如果是新的一个 key，则返回的是 null； //此返回值一般无需接收的！！

*
* 删：map.remove( K类型 key )： 删除 给定的键key在集合中所对应的值value，返回被删除元素的值
* 改：没有直接调用的语句。若要更改某键k对应的值：先remove(此键k，旧值)，然后put(此键k，新值)即可
* 查：map.get( K类型 key )：获取 给定的键key在集合中所对应的值value，返回此值
*
* map.containsKey( K类型 key )：判断集合中是否含有指定的键，返回boolean型
* map.keySet()：获取此map集合中所有的键，储存在Set集合中 并返回此Set集合，
  格式为：Set<键的类型> set = map.keySet();
*
* map.entrySet()：获取此map集合中 所有【键值对】对象，储存在Set集合中，并返回此Set集合，
* 官方：Set< Map.Entry<K键的泛型,V值的泛型>泛型 > entrySet(); //尖括号中：第一个泛型是Map.Entry接口泛型(表示一个键值对；一个键值对就是一个entry对象！！)；嵌套泛型是 键、值的泛型

▲ Map.Entry是Map声明的一个内部接口，此接口为泛型，定义为 Entry<K,V>。
它表示Map中的一个实体（一个key-value对），一个键值对就是一个entry对象！！
此Map.Entry接口中：
某entry实例对象(即 某个键值对).getKey()     //获取该键值对中的键
某entry实例对象(即 某个键值对).getValue()   //获取该键值对中值


* 举例格式为：
  Set< Map.Entry<String即 键的泛型,Integer即 值的泛型> > rst = map.entrySet(); //泛型中还有泛型
* 注：entry 进入
*
* --------------------------
* Map常用子类：先向上转型为Map接口的对象，才可调用上述Map接口中 已有的方法
* java.util.HashMap<K,V>集合 implements Map<K,V>集合            //无序集合HashMap<K,V>，底层是哈希表
* java.util.LinkedHashMap<K,V>集合 implements Map<K,V>集合      //有序集合LinkedHashMap<K,V>，底层是哈希表+链表(保证迭代时的顺序)
* ▲ 确切地说，java.util.LinkedHashMap<K,V>集合 extends(继承) HashMap<K,V>,
* LinkedHashMap<K,V>继承了 HashMap<K,V>
*
* --------------------------
* 1.集合HashMap<K,V>，底层是哈希表
* 基于哈希函数来存储键值对
* 为保证键key的唯一性：键key所属的类型 需要必须重写 hashCode()方法，和equals()方法
*
* ▲ hashCode()方法： Object类中已有的方法，通过 【默认的哈希算法】 返回 【由该对象在内存中的地址转换而成】的 一个整数 (称为 哈希值、哈希码，整数)
* ▲ equals()方法：   Object类中已有的方法，任何类都可以重写，默认比较两个对象的地址值是否相等     //显然只比较地址，不符合实际，需要自定义的重写

equals()方法的重写规则
1 自反性：x.equals(x)必须为true；
2 对称性：x.equals(y)和y.equals(x)返回值必须相等；
3 传递性：x.equals(y)为true，和y.equals(z)为true，那么x.equals(z)也必须为true；
4 一致性：如果对象x和y在equals()中使用的信息没有改变，那么x.equals(y)的值始终不变；
5 非null ： x不是null，y是null，那么x.equals(y)必须为false。


▲ 哈希表的存储原理：
* 当我们向哈希表（如 HashMap、HashSet、HashTable 等）插入一个 object 时，
* 首先调用 hashcode()方法 获得该对象的哈希码(或称 哈希值，下文不赘述了)，
* 通过该哈希码可以直接定位 object 在哈希表中的位置（一般是哈希码对哈希表大小取余），
* 如果该位置没有对象，那么直接将 object 插入到该位置；
* 如果该位置有对象（可能有多个对象，是通过链表实现的），则【调用equals()方法】 将 此处原有对象 与 待入的object 比较，
* 如果相等，则不需要保存 此object，
* 否则(若不相等)，则 根据hash冲突解决算法将对象object插入其他位置；(或将该对象object 插入到 该【含多个对象的链表】中。)


▲ java规定对于HashSet判断是不是重复对象就是通过equals()方法来完成，
这就需要在两个对象equals()方法相等的时候，hash码一定相等（即hashCode()返回的值相等）。
假设两个对象equals()方法相等的时候，hash码不相等，会出现equals()相等的两个对象都插入了HashSet中，这是不允许的。

从而我们有了以下的结论：对于equals()相等的两个对象，其hashCode()返回的值一定相等

* --------------------------
* 2.LinkedHashMap<K,V>  Map接口的哈希表和链表实现，具有可预知的迭代顺序
* 底层原理：哈希表+链表(记录元素的顺序)
* 确切地说，java.util.LinkedHashMap<K,V>集合 extends(继承) HashMap<K,V>,
* LinkedHashMap<K,V>继承了 HashMap<K,V>
*
*  */



import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class Demo01Map {
    public static void main(String[] args) {
        /* 创建集合对象时，此map若要使用Map接口中的公共方法时，使用多态写法：
        * Map<String, String> map = new HashMap<>();
        * */
        Map<String, String> map = new HashMap<>(); //多态写法，左父(Map接口)，右子(HashMap实现类)
        //注意：实现类与接口之间只有接口和实现的关系，再无其他的关系(没有所谓的 继承关系嗷)。
        String a1 = map.put( "A01","小明" );
        System.out.println( a1 ); //null。 解释：put方法 返回 此key对应的前一个value；因为是新的key所以这里返回null
        System.out.println( map.get("A01") );
        System.out.println( "----------------" );

        map.put( "A02","啦啦" );
        map.put( "A03","大师兄" );
        map.put( "A04","嗯" );
        map.put( "A05","佛了" );

        /*
        * 注意：
        * 1.遍历Collection接口下的 (实现类)的集合时：
        * 只能使用 增强型for循环 或 迭代器迭代 来遍历！！
        *
        * 2.遍历Map接口下的 (实现类)的集合map时：
        * 因为map中的数据是 键值对 形式的，所以：
        * 使用 map.keySet() 取出所有的键并存在一个集合Set中
        * 然后遍历此集合Set：
        * 因为Set是属于Collection接口的实现类，所以遍历Set时可用 增强型for循环 或 迭代器迭代 来遍历！！
        *
        * */


        //Map集合 遍历键 取各键对应的值
        Set<String> keys = map.keySet(); //返回所有键 的Set集合
        System.out.println( "遍历键 取各键对应的值，增强型for遍历！" );
        for( String oneKey : keys ){ //法1.使用增强for循环，遍历此Set类型的keys集合
            String oneValue = map.get( oneKey ); //在map键值对集合中，根据此键 拿到此值
            System.out.print( oneValue+" " );
        }
        System.out.println( "\n" ); //换行
        Iterator<String> it = keys.iterator(); //法2.使用迭代器，从keys表示的集合中生成一个迭代器嗷
        System.out.println( "遍历键 取各键对应的值，迭代器遍历！" );
        while( it.hasNext() ){ //典型的迭代器遍历
            String oneKey = it.next(); //在 所有键 的集合Set中，取出下一个键 【与增强for循环相比，迭代器只多了 拿“键” 这一步】
            String oneValue = map.get( oneKey ); //在map键值对集合中，根据此键 拿到此值
            System.out.print( oneValue+" " );
        }
        System.out.println(); //换行
        System.out.println( "------------------" );
        /* Map集合 (使用entry对象)遍历键值对，取各键对应的值
        * map.entrySet()：获取此map集合中 所有【键值对】对象，储存在Set集合中，并返回此Set集合，格式为：
           Set< Map.Entry<String键的泛型,Integer值的泛型> > rst = map.entrySet(); //泛型中还有泛型
           第一个泛型：Map.Entry接口(对象)泛型，表示一个键值对(这就是一个键值对的数据类型！！一个键值对就是一个entry对象！！写在增强型for循环中是必须的)
           嵌套的泛型：键、值的泛型
        * */
        Set< Map.Entry<String,String> > kv = map.entrySet(); //kv即 key-value 键值对
        //某个键值对的类型：Map.Entry<键的泛型，值的泛型>，一个键值对就是一个entry对象，写在增强型for循环中是必须的
        System.out.println( "(使用entry对象)遍历键值对，增强型for遍历！" );
        for( Map.Entry<String,String> oneKV: kv ){
            System.out.print( oneKV.getKey()+"：" );  //获取某个键值对的键
            System.out.print( oneKV.getValue()+"  " ); //获取某个键值对的值
        }
        System.out.println(); //换行
        System.out.println(); //换行
        System.out.println( "(使用entry对象)遍历键值对，迭代器遍历！" );
        Iterator< Map.Entry<String,String> > itKV = kv.iterator(); //在此键值对集合kv上，建立迭代器 it_kv
        while( itKV.hasNext() ){ //易知迭代器中每个元素都是一个键值对
            System.out.print( itKV.next()+"  " ); //直接取出下一个元素(就是取出下一个键值对了)
        }

    }
}
