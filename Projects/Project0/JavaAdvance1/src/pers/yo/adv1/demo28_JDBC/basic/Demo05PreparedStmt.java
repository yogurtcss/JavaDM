package pers.yo.adv1.demo28_JDBC.basic;

/* 定义SQL语句时，使用 PreparedStatement
*  */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement; //预编译的SQL语句 的对象
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo05PreparedStmt {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement  preStmt = null; //以后将用preStmt 代替原本 静态的stmt
        ResultSet rs = null;

        try{
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5306/db1",
                    "root",
                    "root"
            );
            String sql = " select * from stu where sno=? "; //使用占位符 问号?
            //注意，这里的方法名prepare_statement，prepare没有d！
            preStmt = conn.prepareStatement( sql ); //把sql字符串装入 preStmt中
            /* 给？赋值：
			* 方法： preStmt实例对象.setXxx(参数1,参数2)
			*   参数1：？的位置编号 从1 开始
			*	参数2：？的值
            *  */
            preStmt.setString( 1, "A02" );
            /* 静态的sql语句对象 stmt.executeQuery(String sql语句) 需要传入sql字符串；
            * ——因为静态sql语句对象起始是null的，stmt本身不携带sql语句
            *
            * 而预编译的sql语句对象 preStmt.executeQuery(空参) 不需要传入sql字符串
            * ——因为在上面，已经把sql字符串装入 preStmt中，preStmt本身就带着sql语句了
            *
            * preStmt.executeUpdate(空参)也是同理，不传入sql字符串！！
            *  */
            rs = preStmt.executeQuery();

            while( rs.next() ){
                /* rs.getXxx(参数)：获取数据；
	            *    Xxx：代表数据类型，  如： int getInt(int i)，String getString(String s)
	            *   * 参数：
	            *    1. int i：代表列的编号,从1开始   如： getString(1)
	            *    2. String s：代表列名称。 如： getString("balance")
                *  */
                String sname = rs.getString( "sname" );
                String sex = rs.getString( "sex" );
                int math = rs.getInt( 4 ); //math在第4列
                int PE = rs.getInt( 5 ); //PE在第5列
                System.out.println( sname+"-"+sex+"-"+math+"-"+PE );
            }
        }catch( ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }finally{
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
    }
}
