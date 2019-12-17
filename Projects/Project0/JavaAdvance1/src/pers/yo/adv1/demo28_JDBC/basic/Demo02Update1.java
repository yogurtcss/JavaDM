package pers.yo.adv1.demo28_JDBC.basic;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Demo02Update1 {
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
            String sql = "update stu set sname='update咯！' where sno='A05'";
            //若执行DDL 数据定义语句(如create操作)，则count没有返回结果的(count为0)
            int count = stmt.executeUpdate( sql );
            if( count>0 ){
                System.out.println( "操作成功啦！" );
            }else{
                System.out.println( "操作失败！" );
            }
        }catch( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
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
