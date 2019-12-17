package pers.yo.utils.JDBCs;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.Properties;
import java.io.InputStream;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCUtils2 {
    private static DataSource ds;

    static{
        try{
            //1.加载配置文件
            Properties prop = new Properties();
            InputStream is = JDBCUtils2.class.getClassLoader().getResourceAsStream( "druid.properties" );
            prop.load(is);
            //2.获取数据库连接池对象
            ds = DruidDataSourceFactory.createDataSource( prop );

        }catch( IOException e ){
            e.printStackTrace();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    //获取连接池
    public static DataSource getDataSource(){
        return ds;
    }


    //获取连接对象
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    //释放资源，可用 方法的 重载
    public static void close( PreparedStatement preStmt, Connection conn ){
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

    //方法的重载
    public static void close( ResultSet rs, PreparedStatement preStmt, Connection conn ){
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
