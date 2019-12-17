package pers.yo.adv1.demo28_JDBC.dataSource;

//review
import pers.yo.utils.JDBCs.JDBCUtils2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo02Druid4 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try{
            conn = JDBCUtils2.getConnection();
            String sql = "select * from stu where PE=?"; //使用预编译SQL语句，在执行时不要传入sql语句！！
            preStmt = conn.prepareStatement( sql );
            preStmt.setInt( 1,60 );
            ResultSet rs = preStmt.executeQuery(); //执行时，不要再传入sql语句！
            while( rs.next() ){ //游标指向下一行数据
                String sname = rs.getString( "sname" );
                System.out.println( sname );
            }

        }catch( SQLException e ){
            e.printStackTrace();
        }finally{
            JDBCUtils2.close( preStmt, conn );
        }
    }
}
