package pers.yo.adv1.demo28_JDBC.dataSource;

import javax.sql.DataSource;
// DataSource 对象表示的物理数据源的连接。是 DriverManager类的替代项

import com.mchange.v2.c3p0.ComboPooledDataSource; //包名好长！！
import java.sql.Connection;
import java.sql.SQLException;

public class Demo01C3P0 {
    public static void main(String[] args) throws SQLException {
        //1.创建数据库连接池对象，使用默认配置
        DataSource ds = new ComboPooledDataSource();
        //获取数据库连接池对象，使用指定名称的配置
        //DataSource ds1 = new ComboPooledDataSource( "otherc3p0" );
        //2.获取连接对象
        //Connection conn = ds.getConnection();
        //System.out.println( conn ); //务必要把 数据库驱动jar包放入 libs (项目依赖的)目录下！！
        /* c3p0-config.xml 该放在哪？
        *
        * java 中的 classpath 是当前系统所涉及的 class 字节码的存放路径，不是一个、两个地方，
        * 把包含该文件的文件夹标记为 “Source Root” 即可把该文件夹加入 classpath，
        * 已知，src文件夹已被我标记为  “Source Root”，所以 src文件夹也加入了 classpath中
        *
        * 所以，c3p0-config.xml 该放在哪？官方英文文档：把c3p0-config.xml放在classpath的最顶层目录中
        * 直接将c3p0-config.xml文件放在src根目录下，不要放入src中任何一个子目录中！（已验证，是正确的；官方英文文档就是这样的描述）
        * 系统会自动到这个指定的地方，寻找这个配置文件；
        *
        * 输出的一堆红色字，是“日志信息”，日后再说；
        * 日志，即 把一些提示信息记录下来
        *  */
        System.out.println( "---------------" );
        //循环，获取多个连接对象，在配置文件中，max最大获取数为10
        for( int i=0; i<11; i++ ){
            Connection conn = ds.getConnection();
            System.out.println( i+":"+conn );
            if( i==5 ){
                conn.close(); //把连接对象归还到 连接池中
            }
        }


    }
}
