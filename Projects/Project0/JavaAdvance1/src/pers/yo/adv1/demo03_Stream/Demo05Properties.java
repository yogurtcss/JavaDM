package pers.yo.adv1.demo03_Stream;

/* Properties属性集

【老番新看环节】
之前学过的所有集合(如HashMap等)：可以存储null键和null值
Hashtable集合：不能存储null键、不能存储null值

Hashtable集合和Vector集合均被废弃了，
但Hashtable的子类Properties【复数】仍在使用：
Hashtable的子类Properties是唯一和IO流相结合的集合；
Properties 可保存在流中，或从流中加载

java.util.Properties extends Hashtable
Properties表示一个持久的属性集；它使用键值对存储数据：键和值的数据类型都是【字符串】！！
▲ 从Properties集合中取出某键key对应的值value时，可能要做强制类型转换

-----------------------------------------------
因为 Properties是Hashtable 的子类，也就是 Map 类的成员
可以通过 Map 的方式取出该集合中的元素。
该集合中存储的都是【字符串信息】，没有泛型定义。

Properties 中没有定义泛型。
所以在通过 【取所有键的集合】keySet()  和 【取所有键值对的集合】entrySet() 方式取得相应的集合结果时，
要定义成 Set<Object>  和 Set< Map.Entry<Object,Object> > 类型，
最后在取得具体键、值时再通过强制类型转换为 String 即可，否则会报 “不兼容的类型” 错误。

-----------------------------------------------
构造方法：public Properties() 创建一个空的属性列表

基本的存储方法：Properties使用键值对存储数据：键和值的数据类型都是【字符串】！！
public Object setProperty( String key, String value )：保存一对属性(键和值都是字符串型！！)
public String getProperty( String key )：根据所给的键名，在此属性列表中寻找对应的值
public Set<String> stringPropertyNames()：返回 所有键的名称 的集合

与流相关的方法：store、load
1.store：把Properties集合中的临时数据【即 某一给定的字节输出流/字符输出流中的临时数据】，写入到硬盘中(以实现 持久化存储)
void store(   OutputStream out字节输出流-不能写入中文,    String comments注释，一般使用空字符串""    )
void store(   Writer writer字符输出流-可以写入中文,       String comments注释，一般使用空字符串""    )

▲ 注：形参String comments注释，用来解释说明此保存的文件的用途；不能使用中文(否则产生乱码)；默认是Unicode编码

2.load：把硬盘中文件的数据(键值对)，以“输入流”的形式，读取到Properties集合中使用
void load( InputStream字节输入流-不能读取含有中文的键值对   inStream )
void load( Reader字符输入流-可以读取含有中文的键值对        reader )

*  */

import java.util.Properties;
import java.util.Set;
import java.util.Iterator;

public class Demo05Properties {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty( "A01","北洛" );
        prop.setProperty( "A02","小缨子" );
        prop.setProperty( "A03","柿饼" );
        System.out.println( "A01对应的值为："+prop.getProperty("A01") );
        System.out.println( "A02对应的值为："+prop.getProperty("A02") );
        System.out.println( "A03对应的值为："+prop.getProperty("A03") );
        System.out.println( "--------------------------" );
        //取出所有键的名称，储存在集合keys中；
        Set<String> keys = prop.stringPropertyNames(); //可使用 增强for循环 或迭代器遍历 ，取出各键对应的值
        System.out.println( "▲ 取出各键对应的值：我要使用增强for循环喽！" );
        for( String oneKey: keys ){
            System.out.println( oneKey+" 对应的值为："+prop.getProperty(oneKey) );
        }

        System.out.println( "▲ 取出各键对应的值：我要使用迭代器喽！" );
        Iterator<String> it = keys.iterator(); //在此keys集合上，建立迭代器
        while( it.hasNext() ){
            String oneKey = it.next();
            System.out.println( oneKey+" 对应的值为："+prop.getProperty(oneKey) );
        }


    }
}
