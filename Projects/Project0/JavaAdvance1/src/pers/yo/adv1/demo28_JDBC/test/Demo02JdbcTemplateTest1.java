package pers.yo.adv1.demo28_JDBC.test;

/* 练习：
1. 修改 学号A03 的 math 为 38
2. 任意添加一条记录
3. 删除刚才添加的记录
4. 查询 学号为B01 的记录，将其封装为Map集合
5. 查询所有记录，将其封装为List
6. 查询所有记录，将其封装为Student实例对象的List集合
7. 查询总记录数
*  */

import pers.yo.utils.JDBCs.JDBCUtils2; //使用Spring JdbcTemplate来做
import pers.yo.adv1.demo28_JDBC.basic.Student;
import pers.yo.adv1.demo28_JDBC.basic.StuMapper; //手动实现的RowMapper

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.sql.SQLException;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class Demo02JdbcTemplateTest1 {
    public static void main(String[] args) {
        JdbcTemplate temp = new JdbcTemplate( JDBCUtils2.getDataSource() ); //获取JdbcTemplate实例对象

        System.out.println( "1. 修改 学号A03 的 math 为 38" );
        String sql1 = " update stu set math=? where sno=? ";
//        int count1 = temp.update( sql1, 38, "A03" ); //有返回值的 “修改”
//        System.out.println( "影响的行数为："+count1+" 行；" );

        System.out.println( "-----------------------------" );
        System.out.println( "2. 任意添加一条记录" );
        String sql2 = " insert into stu(sno,sname,sex,math,PE) values(?,?,?,?,?) ";
        // ▲ 无返回值的：void execute( String sql )，直接执行sql语句，【但不能为sql语句中的?号赋值！！】很少用！！
        // temp.execute( sql2 )
//        int count2 = temp.update( sql2, "B03","临时数据！","女",22,33 );
//        System.out.println( "影响的行数为："+count2+" 行；" );

        System.out.println( "-----------------------------" );
        System.out.println( "3. 删除刚才添加的记录" );
        String sql3 = " delete from stu where sno=? ";
//        int count3 = temp.update( sql3, "B03" );
//        System.out.println( "影响的行数为："+count3+" 行；" );

        System.out.println( "-----------------------------" );
        System.out.println( "4. 查询 学号为B01 的记录，将其封装为Map集合" );
        String sql4 = " select * from stu where sno=? ";
        Map<String,Object> rs4 = temp.queryForMap( sql4, "B01" );
        Set< Map.Entry<String,Object> > rs4KV = rs4.entrySet(); //获取结果集的 键值对集合 rs4_key_value
        Iterator< Map.Entry<String,Object> > it_rs4KV = rs4KV.iterator(); //使用迭代器迭代之
        while( it_rs4KV.hasNext() ){
            Map.Entry<String,Object> oneKV = it_rs4KV.next(); //取出下一个键值对
            String oneKey = oneKV.getKey(); //键
            Object oneValue = oneKV.getValue(); //值
            System.out.println( oneKey+"="+oneValue );
        }
        System.out.println( "---------遍历键" );
        Set<String> rs4Keys = rs4.keySet();
        Iterator<String> it_rs4Keys = rs4Keys.iterator(); //在键的集合上建立迭代器
        while( it_rs4Keys.hasNext() ){
            String oneKey = it_rs4Keys.next();
            Object oneValue = rs4.get( oneKey );
            System.out.println( oneKey+"="+oneValue );
        }

        System.out.println( "-----------------------------" );
        System.out.println( "5. 查询所有记录，将其封装为List" );
        String sql5 = " select * from stu ";
        List< Map<String,Object> > list5 = temp.queryForList( sql5 );
        for( Map<String,Object> oneKV : list5 ){ //增强for循环，遍历每一个键值对
            System.out.println( oneKV );
        }

        System.out.println( "-----------------------------" );
        System.out.println( "6. 查询所有记录，将其封装为Student实例对象的List集合" );
        String sql6 = sql5; //直接用sql5的语句了
        List<Student> listStu = temp.query(
                sql6,
//                new StuMapper()  //【手动映射】手动实现RowMapper接口的实现类的 实例对象！！
                new BeanPropertyRowMapper<Student>(Student.class) //【自动映射】使用自带的RowMapper接口的实现类的 实例对象！
        );
        for( Student one : listStu ){
            System.out.println( one );
        }

        System.out.println( "-----------------------------" );
        System.out.println( "7. 查询总记录数(用到了聚合函数count)" );
        String sql7 = " select count(*) from stu ";
        int count7 = temp.queryForObject( sql7, Integer.class );
        System.out.println( "总记录数为："+count7+" 行。" );
    }
}
