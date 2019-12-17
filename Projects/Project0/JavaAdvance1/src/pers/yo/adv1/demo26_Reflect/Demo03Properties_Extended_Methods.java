package pers.yo.adv1.demo26_Reflect;

/* Properties Extended Methods
Properties集合 扩展 方法

一、什么是配置文件？( *.properties后缀的文件 )
在 Java 中，其配置文件常为.properties 文件，格式为文本文件，
JDK内置的 java.util.Properties 类支持.properties文件的读写，
为我们操作 .properties 文件提供了便利。

【在demo03_Stream中学过的】
java.util.Properties extends Hashtable
Properties表示一个持久的属性集；它使用键值对存储数据：键和值的数据类型都是【字符串】！！

Hashtable集合和Vector集合均被废弃了，
但Hashtable的子类Properties【复数】仍在使用：
Hashtable的子类Properties是唯一和IO流相结合的集合；
Properties 可保存在流中，或从流中加载

----------------------------------------------------
二、*.properties配置文件的格式：键值对，即 “键 = 值”
# 表示注释
key键 = value值             # 注释：  “=”左边为key键(代码中的变量)； “=”右边为value值(实际配置的值)

#以下为数据库表信息
dbTable = mytable
#以下为服务器信息
ip = 192.168.0.9

#properties 文件特点与格式：
#(1) 键值对的格式，键=值   【或】   键(空格 √)=(空格 √)值(后无空格 √)
#     √ 表示 书写合法， × 表示 书写不合法
#     =等号前，键key：代码中的变量名(不是字符串常量，所以没有双引号！！)
#     =等号后，值value：代码中的变量，或 字符串常量(加引号)，所以 =等号后面的双引号不会被忽略！！
#(2) 关于 “空格”：
#    键值对 = 等号前后的空格或制表符自动忽略
#         - 即 键(空格)=(空格)值；等号后面、值前面的空格将自动忽略
#    值value后面的空格，不会忽略；【注意】value值后面不要带空格！！否则出现不合预期的错误
#(3) 关于字符串的双引号""：值value中的双引号不会被忽略！！
#(4) 名值对写完后，结尾没有分号
#(5) 关于 # 号：
#    # 井号后面内容，为注释，忽略
#    #井号的注释 和 键值对 不能同在一行！！！否则(若两者在同一行)，编辑器会 将井号注释视为 同一行中value值的内容


# ----------------------------------------------
#正确的properties配置文件，但不推荐的写法
# aaa=wu\
# kong
# b
# bb    =     bajie(值后面无空格)   #键值对 = 等号前后的空格或制表符自动忽略
# ----------------------------------------------
#格式良好的properties文件，推荐的写法
# aaa=wukong
# bbb=bajie
# ----------------------------------------------


-----------------------以下为：test1.properties配置文件中的内容-----------------------
####假设，这里是config.properties文件,存储数据库信息####
# 注： √ 表示 书写合法， × 表示 书写不合法

####假设，这里是config.properties文件,存储数据库信息####
# 注： √ 表示 书写合法， × 表示 书写不合法

#数据库ip；▲ 键值对 = 等号前后的空格或制表符自动忽略
connip1 = 192.168.10.29
#井号的注释 和 键值对 不能同在一行！！！否则(若两者在同一行)，编辑器会 将井号注释视为 同一行中value值的内容
connip2_# = 192.168.10.29  #注释嗷！否则(若两者在同一行)，编辑器会 将井号注释视为 同一行中value值的内容

#用户名    ▲ 键(空格 √)=(空格 √)值；等号后面、值前面的空格将自动忽略
#在这里，键=(空格空格空格空格 √)值(后无空格 √)；值后面无空格！！
username1_front_space=                 uesr1_front_space

#在这里。键(空格 √)=(空格 √)值(空格 ×)
#值后面的空格，编辑器将提示 trailing(尾随的) spaces(空格)，智能嗷！
username2_after_space = user2_after_space

#键(无空格 √)=(无空格 √)值(后无空格 √)，太标准(standard)了，有点不喜欢
username3_standard=user3_standard

#键(空格 √)=(空格 √) "值"    =等号后面的双引号不会被忽略！！字符串常量？值确定不是变量？视具体情况而定
username4_string = "user4_string"
-----------------------test1.properties配置文件中的内容结束-----------------------




----------------------------------------------------
三、Properties类的常用方法

1.创建Properties类的实例对象prop
new Properties();

2.与流相关的方法：store、load
(1)store：把Properties集合中的临时数据【即 某一给定的字节输出流/字符输出流中的临时数据】，写入到硬盘中(以实现 持久化存储)
void store(   OutputStream out字节输出流-不能写入中文,    String comments注释，一般使用空字符串""    )
void store(   Writer writer字符输出流-可以写入中文,       String comments注释，一般使用空字符串""    )

▲ 注：形参String comments注释，用来解释说明此保存的文件的用途；不能使用中文(否则产生乱码)；默认是Unicode编码

(2)load：把硬盘中文件的数据(键值对)，以“输入流”的形式，读取到Properties集合中使用
void load( InputStream字节输入流-不能读取含有中文的键值对   inStream )
void load( Reader字符输入流-可以读取含有中文的键值对        reader )

3.String getProperty(String key)：也就是通过指定的键key ，获取key所对应的值value
4.public Set<String> stringPropertyNames()：返回 所有键的名称 的集合

5.Object setProperty(String key, String value)：调用 父类Hashtable 的方法put 来新增一个键(key)值(value)对
没有专门的“修改”方法。若要修改key对应的值value，只能：先删掉原键值对，然后新增key-valueNew键值对，其中valueNew为新的值

6.clear()：清除所有装载的 键值对。该方法在基类中提供。

----------------------------------------------------

四、Properties类 读、写配置文件的步骤
//创建Properties类的实例对象prop
Properties prop = new Properties();

//通过输入流 读入配置文件：可以直接输入配置文件的绝对地址，还可以通过“类加载器”找到配置文件
InputStream in = new InputStream( "XXX.properties"硬盘上的配置文件 );   //直接输入配置文件的绝对地址

*  */

import java.io.InputStream; //字节输入流

import java.util.Properties; //唯一与“流”相关的集合嗷！
import java.util.Set;
import java.util.Iterator;
import java.util.Map; //Map.Entry 取键值对

/* test1.properties配置文件的路径：
* 在 本模块 编译输出文件夹 的总文件夹(以模块名命名) 下的：MyProperties文件夹
* 即 JavaAdvance1文件夹/MyProperties文件夹/test1.properties文件夹
*
* test1.properties配置文件的内容，见上文
*  */
public class Demo03Properties_Extended_Methods {
    public static void main(String[] args) throws Exception {
        //获取当前类的 “类_类型”实例对象 的类加载器
        ClassLoader cld = Demo03Properties_Extended_Methods.class.getClassLoader();
        //将.properties配置文件以“字节输入流”的形式读入
        InputStream is = cld.getResourceAsStream("MyProperties/test1.properties");
        //新建一个prop集合，注意，Properties没有泛型！！
        Properties prop = new Properties();
        //load：把硬盘中文件的数据(键值对)，以“输入流”的形式，读取到Properties集合中使用
        prop.load( is ); //需要声明异常 throws，在这里我嫌麻烦直接声明一个大的异常Exception
        //遍历此集合prop，取所有键 或 所有键值对
        //取出prop中所有键，以String型存入一个集合Set中
        Set<String> keys = prop.stringPropertyNames();
        //遍历此(包含所有键的)集合Set，在这里使用迭代器
        Iterator<String> itKeys = keys.iterator();
        while( itKeys.hasNext() ){
            String oneKey = itKeys.next();
            System.out.println( oneKey+"="+prop.getProperty(oneKey) );
        }

        /* # 所以，读取 此.properties配置文件, 通过 key 获取值，得到结果为：
        # connip 为 192.168.10.29
        # connip_# 为 192.168.10.29  #注释嗷！否则(若两者在同一行)，编辑器会 将井号注释视为 同一行中value值的内容
        # username1_front_space 为 uesr1_front_space 正常
        # username2_after_space 为 user2_after_space(许多空格) 这里要注意，user2_after_space 后面有许多个空格
        # username3_standard 为 user3_standard 正常
        # username4_string 为 “user4_string” 这里要注意，值，包括双引号。
        *  */


        /* 因为 Properties是Hashtable 的子类，也就是 Map 类的成员
        可以通过 Map 的方式取出该集合中的元素。
        该集合中存储的都是字符串信息，没有泛型定义。

        Properties中没有定义泛型。
        所以在通过 【取所有键的集合】keySet()  和 【取所有键值对的集合】entrySet() 方式取得相应的集合结果时，
        要定义成 Set<Object>  和 Set< Map.Entry<Object,Object> > 类型，
        最后在取得具体键、值时再通过强制类型转换为 String 即可，否则会报 “不兼容的类型” 错误。
        *  */


        /* 取出所有键值对，放入Set集合 kv中
        * 注意【这里易忘的】，Properties集合没有泛型
        * prop集合中，【由所有键值对组成的Set集合】中的泛型为：Map.Entry每一个键值对，
        * 键值对的类型：键-Object，值-Object
        *  */
        System.out.println( "---------------------" );
        Set< Map.Entry<Object,Object> > kv = prop.entrySet();
        /* 在此所有的键值对集合kv上，建立迭代器遍历
        * 注意，迭代器中的泛型也是 Map.Entry<Object,Object> 嗷！
        *  */
        Iterator< Map.Entry<Object,Object> > itKV = kv.iterator();
        while( itKV.hasNext() ){
            Map.Entry<Object,Object> oneEntry = itKV.next();
            System.out.println( oneEntry.getKey()+"="+oneEntry );
//            System.out.println( oneEntry );
        }
    }
}
