package pers.yo.adv1.demo28_JDBC.basic;

/* JDBC练习1：对表添加一行数据
* 步骤如下：8步
* 1.导入数据库驱动Jar包至lib目录下
* 2.注册驱动
* 3.从DriverManager类中，获取数据库连接的实例对象conn
* 4.从数据库连接的实例对象conn中，获取执行sql语句的对象stmt
* 5.定义sql语句
* 6.执行sql语句 stmt.executeXXX(sql)
* 7.处理结果
* 8.释放资源
*  */
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02Insert1 {
    public static void main(String[] args)  {
        Connection conn = null;
        Statement stmt = null;
        try{
            //1.导入数据库驱动jar包
            Class.forName( "com.mysql.jdbc.Driver" ); //2.注册驱动
            //jdbc:mysql://(双杠)主机名称：连接端口号/(单杠)数据库的名称 [?参数=值 可选的]
            conn = DriverManager.getConnection( //3.获得连接对象
                    "jdbc:mysql://localhost:5306/db1",
                    "root",
                    "root"
            );
            stmt = conn.createStatement(); //4.获得执行sql语句的对象stmt
            String sql = "insert into stu(sno,sname,sex,math,PE) " +
                    "values('B01','我是insert！','女',90,60)"; //定义sql语句
            int count = stmt.executeUpdate( sql ); //执行sql语句
            System.out.println( count ); //处理结果
            if( count>0 ){
                System.out.println( "操作成功！" );
            }else{
                System.out.println( "操作失败！" );
            }
        }catch( ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally{ //释放资源：先打开的后关闭，后打开的先关闭
            /* 需要把 stmt、conn提前声明为null空
            *
            * 注意！如果 连接的conn中，因密码写错等 导致conn连接失败，
            * 那么 stmt是一个空方法！！接下来若继续使用stmt.close()，将出现 空指针异常
            * 为避免空指针异常，在关闭stmt资源时作判断
            *
            * 释放conn的资源时，同样的操作
            *
            *  */
            if( stmt!=null ){
                try{
                    stmt.close();
                }catch( SQLException e ){
                    e.printStackTrace();
                }
            } //若stmt为null，那就不需要close释放资源了：因为一开始就没有申请到资源
            if( conn!=null ){
                try{
                    conn.close();
                }catch( SQLException e ){
                    e.printStackTrace();
                }
            }

        }
    }
}
