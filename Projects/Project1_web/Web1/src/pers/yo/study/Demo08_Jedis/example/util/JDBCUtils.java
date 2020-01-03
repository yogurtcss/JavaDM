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

    /* 获取数据库连接池对象
    * 这在 DAO层 操作数据库时，创建JdbcTemplate实例对象 用得着！！
    * DAO类中的私有变量：
    * private JdbcTemplate template = new JdbcTemplate( 【JDBCUtils.getDataSource()】 );
    *  */
    public static DataSource getDataSource(){ //静态方法，由类名直接调用
        return ds;
    }

    //从数据连接池中，获取某个连接对象
    //然后利用此连接对象 进行数据库的操作
    public static Connection getConnection() throws SQLException {
        return( ds.getConnection() );
    }

}
