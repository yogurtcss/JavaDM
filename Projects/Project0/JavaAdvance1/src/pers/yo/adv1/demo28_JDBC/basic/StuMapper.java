package pers.yo.adv1.demo28_JDBC.basic;

/* RowMapper泛型接口中的 mapRow方法——手动对自定义类进行包装；
1.在外部定义一个实现类；
2.使用匿名内部类；
*  */


import org.springframework.jdbc.core.RowMapper;
/* org：全称organization，由企业或者组织提供的java类库
* springframework：Spring框架
* core：核心
*
* jdbc.core 即 JDBC的核心部分
*  */

import java.sql.ResultSet;
import java.sql.SQLException;

import pers.yo.adv1.demo28_JDBC.basic.Student;

public class StuMapper implements RowMapper<Student> {
    //先从结果集rs中getXXX拿到这个【该列名下的这个值】
    //然后，调用实例对象中的(暴露出来的public的)setXXX方法，
    //【逐一地】把getXXX到的值 set进实例对象中

    //若ResultSet结果集中有多行数据，则：传入的是单条记录(单行)， 内部n次调用mapRow方法；
    public Student mapRow( ResultSet rs, int rowNum ){
        Student stu = new Student();
        try{
            /* rs.getXxx(参数)：获取数据；
            *    Xxx：代表数据类型，  如： int getInt(int i)，String getString(String s)
            *   * 参数：
            *    1. int i：代表列的编号,从1开始   如： getString(1)
            *    2. String s：代表列名称。 如： getString("balance")
            *  */
            stu.setSno( rs.getString("sno") );
            stu.setSname( rs.getString( "sname" ) );
            stu.setSex( rs.getString("sex") );
            stu.setMath( rs.getInt(4) );
            stu.setPE( rs.getInt(5) );
        }catch( SQLException e ){
            e.printStackTrace();
        }

        return stu;
    }
}
