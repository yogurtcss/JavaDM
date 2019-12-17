package pers.yo.adv1.demo28_JDBC.basic;

/* JDBC：即 Java DataBase Connectivity
Java数据库连接，用Java语言操作数据库

JDBC本质：1.官方Sun公司定义的一套操作所有关系型数据库的规则(即接口)；
2.各数据库厂商去实现这套接口，提供【数据库驱动jar包】；
3.我们使用JDBC接口编程时，真正执行的代码是 数据库驱动jar包中的实现类

------------------------------------------------------------------
快速使用JDBC编程的通用步骤
1.导入【数据库驱动jar包】
2.注册驱动-只需做一次
3.获取数据库连接对象 Connection
4.定义sql
5.获取执行sql语句的对象 Statement
6.执行sql，接收返回的结果
7.处理结果
8.释放资源(关闭Connection)


------------------------------------------------------------------详解如下：
1.导入【数据库驱动jar包】
    (1)新建libs文件夹，复制mysql-connector-java-5.1.37-bin.jar到项目的libs目录下
	(2)对着lib文件夹，右键 --> Add As Library(添加为库...) --> 默认选择Level为Project Libray项目库，然后确定即可
	这样才真正把数据库驱动jar包加入到项目(的工作空间)中

------------------------------------------------------------------
2.注册驱动-只需做一次：小括号里面是 com.数据库名.jdbc.Driver

问：为什么要 Class.forName ("com.mysql.jdbc.Driver")？
答：JDBC是23种模式中“桥接模式”的典型应用(跳...)；

▲ Java规范中明确规定，
所有的【驱动程序】必须在 静态初始代码块 中将驱动 注册到【驱动程序的管理器】中；

▲ Java.sql.Driver 接口
JDBC 驱动程序需要实现的接口，供数据库厂商使用，不同数据库提供不同的实现；
应用程序通过驱动程序管理器类 (java.sql.DriverManager) 去调用这些 Driver 实现。

▲ 驱动程序管理器 DriverManager的作用：负责管理驱动程序
用来创建连接，它本身就是一个创建 Connection 的工厂，
设计的时候使用的就是 Factory 模式，给各数据库厂商提供接口，各数据库厂商需要实现它；
Connection 接口，根据提供的不同驱动产生不同的连接； Statement 接口，用来发送 SQL 语句；

DriverManager的好处：
1.DriverManager 是管理驱动类
2.可以通过重载的 getConnection（）方法获取数据库，比较方便
3.可以同时传入多个驱动：若注册了多个数据库链接，
则调用 getConnection（）方法是参数的传入不同，就会返回不同的数据库链接。


------------------------
▲ “注册驱动”的大致流程如下：
1.【准备】某厂商实现了Java.sql.Driver接口
并给出此接口的实现类的【实例对象】为 driverXXX (名字是我任意起的)
如MySQL给出的 此接口实现类的实例对象为 driver_MySQL；

2.【注册】调用 Class.forName ("com.mysql.jdbc.Driver")的细节：
  (1)利用反射Class.forName(...)加载一个类进入内存 (获得一个 【类_类型即Class类型】 的 实例对象),
  并执行初始化：使得 【类中的静态变量会被初始化，静态代码块也会被执行】；
  (2)而此静态块中执行的内容正是 “注册驱动”的操作：
     1.先实例化Driver驱动自身，
     向 【 DriverManager类中的 注册驱动函数 java.XX.XX.registerDriver()】 传入自身的Driver实例对象
     //java.sql.DriverManager.registerDriver( new Driver() );
     //new自己，先把自己(驱动) 传入 【注册函数】中

     2.在 【DriverManager类中】的 java.sql.DriverManager.registerDriver()方法中，(传入当前此厂商的driver实例对象后：)
     创建 DriverInfo类的实例对象di，
     把此driver实例对象 作为 di的一个属性"driver" 保存起来，
     // DriverInfo di = new DriverInfo();
     // di.driver属性 = driver实例对象;

     接着，又把此 DriverInfo类的实例对象di   // (注 里面di.driver携带着厂商制造的实例对象)
     add添加到 【CopyOnWriteArrayList类型】的变量registeredDrivers(称为 驱动管理器DriverManager的已注册列表)中；
     // 驱动管理器DriverManager的已注册列表为：
     // private static final CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList();
     这样，当 需注册的驱动(的实例对象) 被添加至 “已注册列表” 中时，此注册操作已完成。

     ▲ 后续：遍历 “已注册列表”，
     使用 【DriverInfo类的实例对象di的driver属性下的 Driver对象】 去做实际的连接数据库的工作，
     注册进来【是什么厂商的数据库驱动】，就相应地连接此厂商本身的数据库嗷！
     // Connection result = di.driver.connect(url, info);

------------------------
注册流程的小结：
(1)把厂商实现的driver实例对象，(以特定的方式，如上文所述)传入 驱动管理器的 “已注册列表”中；
   //后续操作就针对此 “已注册列表” 中的 <已注册驱动> 了
(2)遍历“已注册列表”：让各厂商的实例对象driver 连接 自身厂商的数据库；

------------------------------------------------------------------

* */

import java.sql.DriverManager; //驱动管理器
import java.sql.Connection; //SQL连接对象
import java.sql.Statement; //执行sql语句的对象

public class Demo01JDBC1 {
    public static void main(String[] args) throws Exception {
        //1.导入 数据库驱动jar包
        //2.注册驱动，可能需要声明/处理ClassNotFoundException异常
        Class.forName( "com.mysql.jdbc.Driver" ); //直接声明一个大的异常
        /* 3.获取连接对象
        * DriverManager.getConnection( url-字符串, user-字符串, password-字符串 )
        *
        * MySQL JDBC的URL编写格式：字符串
        * jdbc:mysql://主机名称：连接端口号/数据库的名称 [?参数=值 可选的]
        *
        * user和password都是 字符串形式嗷！
        *  */
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:5306/db1",
                "root", "root");
        //4.定义sql语句
        String sql = "update stu set sname='冲冲冲！' where(sno='A01')"; //IDEA可能提示，需选择data source(选择数据库)
        //5.获取 连接对象conn中，执行sql的实例对象 Statement
        Statement stmt = conn.createStatement();
        //6.执行sql语句
        int count = stmt.executeUpdate( sql );
        //7.处理结果
        System.out.println( count );
        //8.释放资源
        stmt.close();
        conn.close();
    }
}
