package pers.yo.adv1.demo28_JDBC.dataSource;

import pers.yo.utils.JDBCs.JDBCUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo02Druid3 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try{
            conn = JDBCUtils2.getConnection();
            //String sql = " select * from stu where PE= ? ";
            String sql = "update stu set sname=? where sno=?";
            /* 通过conn实例对象 获取 preparedStatement预编译SQL语句
            * 格式：preStmt = conn.prepareStatement( sql );
            *  */
            preStmt = conn.prepareStatement( sql );
            /* preStmt为SQL语句中的问号?赋值
            *  preStmt.setXXX( 第几个问号, 要赋的值 );
            *  */
            //preStmt.setInt( 1,60 ); //第1个问号，要赋的值为60
            preStmt.setString( 1,"重新设置的sname" );
            preStmt.setString( 2,"A02" );

            /* 直接执行 此预编译的sql语句
            * 静态的sql语句对象 stmt.executeQuery(String sql语句) 需要传入sql字符串；
            * ——因为静态sql语句对象起始是null的，stmt本身不携带sql语句
            *
            * 而预编译的sql语句对象 preStmt.executeQuery(空参) 不需要传入sql字符串
            * ——因为在上面，已经把sql字符串装入 preStmt中，preStmt本身就带着sql语句了
            *
            * 我忘了！我写成preStmt.executeQuery(sql语句)，以至于报错！！
            *  */
            //ResultSet rs = preStmt.executeQuery();
            int count = preStmt.executeUpdate();
            System.out.println( count );
//            while( rs.next() ){
//                //取查询结果中的值
//                String sname = rs.getString( "sname" );
//                System.out.println( sname );
//            }
        }catch( SQLException e ){
            e.printStackTrace();
        }finally{
            JDBCUtils2.close( preStmt,conn );
        }

    }
}
