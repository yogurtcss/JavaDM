package demo02_MyDesign.mybatis.utils;

import demo02_MyDesign.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//负责执行SQL语句，并封装结果集
public class Executor {

    public <E> List<E> selectList(Mapper mapper, Connection conn ){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<E> list = new ArrayList<>();
        try{
            //---1.取出mapper中的数据
            String queryString = mapper.getQueryString(); //select * from user
            String resultType = mapper.getResultType(); //demo02_MyDesign.domain.user

            Class<?> domainClass = Class.forName( resultType ); //根据全限定类名，生成封装结果的 Class实例对象嗷

            //---2.获取 PreparedStatement实例对象
            pstm = conn.prepareStatement( queryString );

            //---3.执行SQL语句，获取结果集
            rs = pstm.executeQuery();

            //---4.封装结果集

            while( rs.next() ){
                //实例化要封装的实体类对象
                E obj = (E)domainClass.newInstance();
                //取出结果集的元信息：ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出总列数
                int columnCount = rsmd.getColumnCount();
                //遍历总列数
                for (int i = 1; i <= columnCount; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//要求：实体类的属性和数据库表的列名保持一种
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    writeMethod.invoke(obj,columnValue);
                }
                //把赋好值的对象加入到集合中
                list.add(obj);
            }

        }catch( Exception e ){
            e.printStackTrace();
        }finally{
            release( pstm, rs );
        }

        return list;
    }

    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
