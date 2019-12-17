package pers.yo.adv1.demo28_JDBC.test;

import pers.yo.utils.JDBCs.JDBCUtils1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo01Transaction { //Transaction 事务
    public static void main(String[] args)  {
        Connection conn = null;
        PreparedStatement preStmt1 = null;
        PreparedStatement preStmt2 = null;
        try{
            conn = JDBCUtils1.getConnection();
            //System.out.println( conn );

            //开启事务
            conn.setAutoCommit(false);
            String sql1 = "update stu set math=math-? where sno=?";
            String sql2 = "update stu set math=math+? where sno=?";
            preStmt1 = conn.prepareStatement( sql1 );
            preStmt2 = conn.prepareStatement( sql2 );
            /* 给？赋值的方法 方法： setXxx(参数1,参数2)
            * 参数1：它代表的是第几个问号的位置。即？的位置编号；从1 开始
            * 参数2：？的值
            *  */
            preStmt1.setInt(1,10);
            preStmt1.setString( 2,"A03" );
            preStmt2.setInt( 1,10 );
            preStmt2.setString( 2,"A04" );
            preStmt1.executeUpdate();
            int i = 3/0; //手动制造异常
            preStmt2.executeUpdate();
            //提交事务
            conn.commit();

        }catch( Exception e ){ //不管出现什么异常，都会回滚。所以捕获的是 大的异常
            try{
                if( conn!=null ){ //事务回滚
                    //System.out.println( "--"+conn );
                    conn.rollback();
                }
            }catch( SQLException e1 ){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JDBCUtils1.close2( preStmt1, conn );
            //在上一个语句中，conn已被关闭了，所以这里的conn为null
            JDBCUtils1.close2( preStmt2, null );
        }
    }
}
