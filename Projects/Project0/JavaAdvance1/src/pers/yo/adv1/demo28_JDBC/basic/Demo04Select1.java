package pers.yo.adv1.demo28_JDBC.basic;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Demo04Select1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5306/db1",
                    "root",
                    "root"
            );
            stmt = conn.createStatement();
            String sql = "select * from stu";
            rs = stmt.executeQuery( sql );
            while( rs.next() ){ //游标向下移动一行，判断是否有数据，类似 迭代器、流的while循环
                String sname = rs.getString("sname");
                String sex = rs.getString("sex");
                System.out.println( sname+"--"+sex );
            }
        }catch( ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally{
            if( rs!=null ){ //后打开的先关闭
                try{
                    rs.close();
                }catch( SQLException e ){
                    e.printStackTrace();
                }
            }
            if( stmt!=null ){
                try{
                    stmt.close();
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
}
