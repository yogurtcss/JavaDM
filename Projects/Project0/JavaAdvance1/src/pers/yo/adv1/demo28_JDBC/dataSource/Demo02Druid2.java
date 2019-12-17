package pers.yo.adv1.demo28_JDBC.dataSource;

import pers.yo.utils.JDBCs.JDBCUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Demo02Druid2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = JDBCUtils2.getConnection();
            String sql = "insert into stu values(?,?,?,?,?)";
            preStmt = conn.prepareStatement( sql );
            preStmt.setString( 1,"B02" ); //sno
            preStmt.setString( 2,"Druid工具类嗷！" ); //sname
            preStmt.setString( 3,"女" ); //sex
            preStmt.setInt( 4, 33 ); //math
            preStmt.setInt( 5, 30 );
            //执行sql语句
            preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils2.close( preStmt,conn );
        }
    }
}
