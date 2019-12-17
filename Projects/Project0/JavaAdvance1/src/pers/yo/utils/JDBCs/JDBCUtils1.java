package pers.yo.utils.JDBCs;

/* JDBC工具类
 * 注：一般的工具类 如Maths、Arrays等，里面全都是静态方法，方便直接调用；
 * 所以此工具类下，全是 静态成员变量、静态成员方法，加static关键字
 *
 * 1.抽取出 注册驱动 的方法
 * 2.抽取出 获取连接对象 的方法
 * 3.抽取出 释放资源 的方法
 */

/*
URL 解析：
协议为 (protocol)：http
主机为 (host:port)：www.runoob.com
端口号为 (port): 80 ，以上 URL 实例并未指定端口，因为 HTTP 协议默认的端口号为 80。
文件路径为 (path)：/index.html
请求参数 (query)：language=cn
定位位置 (fragment)：j2se，定位到网页中 id 属性为 j2se 的 HTML 元素位置 。

----------------
对应的方法：
1.public String getPath()  返回 URL路径部分

* */

import java.net.URL; //通过classloader实例对象，获取配置文件
import java.io.FileReader; //文件字符输入流
import java.util.Properties;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCUtils1 {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //文件读取
    static{ //静态代码块：随着类的加载而执行，且只需读取一次即可拿到这些值；
        try{
            Properties prop = new Properties();

            ClassLoader cl = JDBCUtils1.class.getClassLoader();
            URL res = cl.getResource( "MyProperties/JDBC/jdbc.properties" );

            System.out.println( cl.getResource("") ); //这是什么？
//            //file:/H:/ProcExes/JavaFiles/JavaDM/JavaAdvance1/out/production/JavaAdvance1/
//
//            System.out.println (System.getProperty ("java.class.path")); //这又是什么？

            //返回URL的 “路径部分”，即返回此jdbc.properties文件的 全路径字符串
            String path = res.getPath(); // res.getPath()可能引起NullPointerException
//            System.out.println( res );
//            System.out.println( path );

            //通过文件字符输入流FileReader，读入此jdbc.properties文件
            prop.load( new FileReader(path) );
            url = prop.getProperty( "url" );
            user = prop.getProperty( "user" );
            password = prop.getProperty( "password" );
            driver = prop.getProperty( "driver" ); //获取驱动名

            Class.forName( driver ); //注册驱动

        }catch( IOException | ClassNotFoundException | NullPointerException e ){
            e.printStackTrace();
        }
    }

    //获取连接对象conn
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url,user,password );
    }

    //释放资源。后打开的先释放(关闭)
    public static void close1( ResultSet rs, PreparedStatement preStmt, Connection conn ){
        if( rs!=null ){
            try{
                rs.close();
            }catch( SQLException e ){
                e.printStackTrace();
            }
        }

        if( preStmt!=null ){
            try{
                preStmt.close();
            }catch( SQLException e ){
                e.printStackTrace();
            }
        }

        if( conn!=null ){
            try{
                conn.close();
            }catch( SQLException e ){
                e.printStackTrace();
            }
        }
    }

    public static void close2(  PreparedStatement preStmt, Connection conn ){
        //没有结果集rs

        if( preStmt!=null ){
            try{
                preStmt.close();
            }catch( SQLException e ){
                e.printStackTrace();
            }
        }

        if( conn!=null ){
            try{
                conn.close();
            }catch( SQLException e ){
                e.printStackTrace();
            }
        }
    }

}
