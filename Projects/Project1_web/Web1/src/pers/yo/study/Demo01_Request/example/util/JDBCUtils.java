package pers.yo.study.Demo01_Request.example.util;

/* JDBC工具类，使用Druid连接池
* 得会看一下 JDBC工具类怎么写了……忘了……
*  */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //1.定义成员变量
    private static DataSource ds; //数据库连接池对象DataSource

    static{
        try{
            Properties prop = new Properties(); //读取配置文件的集合对象
            //使用ClassLoader加载配置文件，获取字节输入流。注意配置文件的路径嗷！
            //src文件夹下的 ：pers/yo/props/druid.properties 路径确认可用
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("pers/yo/props/druid.properties");
            prop.load(is);

            //获取数据库连接池对象：通过工厂来来获取  DruidDataSourceFactory
            ds = DruidDataSourceFactory.createDataSource( prop );

        }catch( IOException e ){
            e.printStackTrace();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }


    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }

    //获取Connection连接对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}