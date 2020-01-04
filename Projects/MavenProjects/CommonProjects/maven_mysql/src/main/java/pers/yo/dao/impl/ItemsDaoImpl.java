package pers.yo.dao.impl;

import pers.yo.dao.ItemsDao;
import pers.yo.domain.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {
    public List<Items> findAll() throws SQLException { //使用IDEA快速帮我生成需重写的方法
        List<Items> list = new ArrayList<Items>();
        Connection conn = null; //连接对象
        PreparedStatement pstmt = null; //预编译的数据库语句对象
        ResultSet rs = null; //查询结果集

        try{
            /* 加载驱动类，注册驱动
            * 即告诉DriverManager：我要用mysql的驱动，给我返回mysql的连接对象！
            *  */
            Class.forName( "com.mysql.jdbc.Driver" );
            //注册驱动成功后，从DriverManager中取出mysql的连接对象
            conn = DriverManager.getConnection(
                    //我本机mysql数据库的端口是5306！！
                    //url=jdbc:mysql://127.0.0.1:5306/db1
                    "jdbc:mysql://localhost:5306/maven",
                    "root", //用户名
                    "root" //密码
            );
            //准备sql语句
            String sql = "select * from items"; //查询的是items表！！
            pstmt = conn.prepareStatement( sql ); //填入预编译的语句对象
            rs = pstmt.executeQuery(); //执行sql

            while( rs.next() ){
                //---用数据库查询的数据，填入items实例对象中
                Items its = new Items();
                its.setId( rs.getInt("id") );
                its.setName( rs.getString("name") );
                //---将填好的items实例对象放入列表list中
                list.add( its );
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{ //释放资源
            try{ //后打开的先关闭
                rs.close();
                pstmt.close();
                conn.close();
            }catch( NullPointerException e1 ){
                e1.printStackTrace();
            }
        }
        //返回结果
        return list;
    }
}
