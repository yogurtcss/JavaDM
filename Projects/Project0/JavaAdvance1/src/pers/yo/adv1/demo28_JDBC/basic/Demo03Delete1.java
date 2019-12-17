package pers.yo.adv1.demo28_JDBC.basic;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Demo03Delete1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5306/db1",
                    "root",
                    "root"
            );
            stmt = conn.createStatement();
            // String sql = "delete from stu where sno='A01'";
            String sql = " create table stu2( sno varchar(10) ) "; //DDL 数据定义，没有返回值，此时count=0
            int count = stmt.executeUpdate( sql );
            System.out.println( count );
//            if( count>0 ){
//                System.out.println( "操作成功！" );
//            }else{
//                System.out.println( "操作失败！" );
//            }
        }catch( ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally{
            if( stmt!=null ){
                try{
                    stmt.close();
                }catch( SQLException e ){
                    e.printStackTrace();
                }
                try{
                    conn.close();
                }catch( SQLException e ){
                    e.printStackTrace();
                }
            }
        }

    }
}
