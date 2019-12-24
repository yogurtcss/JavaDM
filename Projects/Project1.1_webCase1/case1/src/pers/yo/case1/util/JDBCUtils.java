package pers.yo.case1.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Durid连接池
 */

/*
1.将配置文件druid.properties复制到本项目的src中
    * 在这里我把配置文件复制到了 src/ pers/ yo/ props文件夹中，不想放在全局中
      通过类加载器读取这配置文件的时候，要注意书写的路径了……
      书写路径为 /pers/yo/props/druid.properties 吗？ 【记住，pers前要带斜杠！】
      src文件夹下的 ：/pers/yo/props/druid.properties 路径确认可用

    * 此配置文件中的url需要更改为 我电脑中的配置：端口为5306，使用我自己db1的数据库嗷！
      url=jdbc:mysql://127.0.0.1:5306/db1数据库

    * 在IDEA外部，提前在db1数据库中建好所需的表user，并向表user中插入若干新数据
      列名（全小写嗷！）有 id、username、password

* */
public class JDBCUtils {

    private static DataSource ds ;

    static {

        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流

            /* 2019-12-24 22:57:02
            * 忘记数据源的配置文件要改地方了！
            *  */
            /* 正确的路径：/pers/yo/case1/props/druid.properties
            * 【记住，pers前要带斜杠！】
            *  */
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("/pers/yo/case1/props/druid.properties");
            pro.load(is);

            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }


    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }
}
