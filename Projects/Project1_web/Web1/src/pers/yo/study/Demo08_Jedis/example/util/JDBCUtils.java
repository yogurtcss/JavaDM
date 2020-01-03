package pers.yo.study.Demo08_Jedis.example.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/* 2020-01-03 16:54:10
* 复习 JDBCUtils 的写法！
*  */
public class JDBCUtils {
    /* 因为要在静态方法getDataSource、getConnection中使用这个变量，
    * 所以此变量ds必需声明为静态变量 private static
    *  */
    private static DataSource ds; //私有的静态、全局变量 数据库连接池对象ds

    static{ //静态代码块中，加载配置文件

        try{ //直接用 try...catch把所有读取配置文件的代码包起来
            /* 需要捕获异常的地方：
            * 1.properties集合读取流数据时 prop.load( 流 )
            * 2.根据properties，利用 “大写-Druid数据库连接池工厂类” 初始化数据库连接池对象时
            * ds = DruidDataSourceFactory工厂类.createDataSource( properties实例对象 )
            *  */
            Properties prop = new Properties(); //准备读取配置文件的properties集合对象
            /* 反射中 获得 Class 类_类型实例对象
            * (1)Class.forName( "全路径名: 包名.类名" )
            * (2)类名.class属性
            * (3)任意实例对象.getClass()
            *
            * 注意配置文件的路径！可以手动复制到 out/production/项目名/pers/yo/props下！！
            *  */
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream( "pers/yo/props/druid.properties" );
            prop.load( is ); //正式把配置文件的数据 加载入properties集合中

            //利用 【大写-Druid数据库连接池工厂类】.createDataSource( prop )来初始化 数据库连接池对象
            ds = DruidDataSourceFactory.createDataSource( prop );

        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    /* 类的静态方法：从数据连接池中，获取某个连接对象
    * 然后利用此连接对象 进行数据库的操作
    * 静态方法只能调用静态方法（或者变量），非静态方法可以调用静态方法（或者变量）
    * 因为此静态方法getDataSource()中调用了变量ds，所以ds必需声明为 静态变量！private static
    *  */

    /* 获取数据库连接池对象
    * 这在 DAO层 操作数据库时，创建JdbcTemplate实例对象 用得着！！
    * DAO类中的私有变量：
    * private JdbcTemplate template = new JdbcTemplate( 【JDBCUtils.getDataSource()】 );
    *  */
    public static DataSource getDataSource(){ //静态方法，由类名直接调用
        return ds;
    }

    /* 类的静态成员 (变量和方法) 属于类本身，在类加载的时候就会分配内存，可以通过类名直接去访问（类名。方法 | 类名。变量）；
    非静态成员（变量和方法）属于类的对象，所以只有在类的对象产生（创建类的实例）时才会分配内存，然后通过类的对象（实例）去访问。

    在一个类的静态成员中去访问其非静态成员之所以会出错：
    是因为 【在类的非静态成员不存在的时候】，类的静态成员就已经存在了，
    此时访问一个内存中不存在的东西【非静态成员】当然会出错。
    *  */


    /* 类的静态方法：从数据连接池中，获取某个连接对象
    * 然后利用此连接对象 进行数据库的操作
    * 静态方法只能调用静态方法（或者变量），非静态方法可以调用静态方法（或者变量）
    * 因为此静态方法getConnection()中调用了变量ds，所以ds必需声明为 静态变量！private static
    *  */
    public static Connection getConnection() throws SQLException {
        return( ds.getConnection() );
    }

}
