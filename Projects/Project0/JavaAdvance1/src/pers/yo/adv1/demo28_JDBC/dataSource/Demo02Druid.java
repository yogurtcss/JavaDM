package pers.yo.adv1.demo28_JDBC.dataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Demo02Druid {
    public static void main(String[] args) throws Exception {
        //1.导入jar包，放入libs项目依赖的文件夹下
        //2.定义配置文件
        //3.加载配置文件
        Properties prop = new Properties();
        InputStream is = Demo02Druid.class.getClassLoader().getResourceAsStream("druid.properties");
        //System.out.println( is );

        /* src也属于classpath的子文件夹，
        * 把druid.properties放在src文件夹下，然后通过 类_类型的 类加载器读取此配置文件后：
        * 此配置文件也会被复制到： out编译输出文件夹/production/JavaAdvance1(模块名)
        * 本质上，类加载器还是会直接读取 out编译输出文件夹/production/JavaAdvance1(模块名)下的资源文件！！
        *
        * 所以，以后把资源文件直接放在src文件夹 或 直接放在 out编译输出文件夹下，都是同理的
        *  */
        //正式加载配置文件进 prop集合中
        prop.load( is );
        /* 4.获取连接池对象
        * DataSource ds = DruidDataSourceFactory.createDataSource( prop含配置文件信息的集合对象 );
        *  */
        DataSource ds = DruidDataSourceFactory.createDataSource( prop );
        //5.获取池中一个连接对象
        Connection conn = ds.getConnection();
        System.out.println( conn );
    }
}
