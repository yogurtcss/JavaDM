package pers.yo;

import oracle.jdbc.driver.OracleTypes;
import org.junit.Test;

import java.sql.*;

/* 2020-02-01 21:18:50
* 测试全部通过！Java和Oracle数据库打通了！
* */
public class OracleDemo {

    @Test
    public void javaCallOracle() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到Connection连接：用户名yo1,密码root
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "yo1", "root");
        //得到预编译的Statement对象
        PreparedStatement pstm = connection.prepareStatement("select * from emp where empno = ?");
        //给参数赋值
        pstm.setObject(1, 7788);
        //执行数据库查询操作
        ResultSet rs = pstm.executeQuery();
        //输出结果
        while(rs.next()){
            System.out.println( "查询结果是："+rs.getString("ename") );
        }
        //释放资源
        rs.close();
        pstm.close();
        connection.close();
    }

    /**
     * java调用存储过程
     * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
     *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
     * @throws Exception
     */
    @Test
    public void javaCallProcedure() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到Connection连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "yo1", "root");
        //得到预编译的Statement对象
        CallableStatement pstm = connection.prepareCall("{call p_yearsal(?, ?)}");
        //给参数赋值
        pstm.setObject(1, 7788);
        pstm.registerOutParameter(2, OracleTypes.NUMBER);
        //执行数据库查询操作
        pstm.execute();
        //输出结果[第二个参数]
        System.out.println( "存储过程的结果是："+pstm.getObject(2));
        //释放资源
        pstm.close();
        connection.close();
    }
}
