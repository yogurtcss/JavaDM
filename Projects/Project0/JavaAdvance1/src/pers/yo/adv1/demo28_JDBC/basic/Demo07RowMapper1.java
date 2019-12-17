package pers.yo.adv1.demo28_JDBC.basic;

/* RowMapper泛型接口 测试

一、概述
spring 中的 RowMapper：可以将数据中的每一行数据(逐行地)封装成用户定义的类。
我们在数据库查询中：(1)如果返回的类型是用户自定义的类型 (其实在数据库查询中大部分返回的都是自定义的类，如Person类)， 则需要手动包装；
(2)如果是 Java 已有的类型(如String)，则不需要自己手动封装。
-------------------------------------------------------------

二、使用：实现RowMapper泛型接口，重写此接口中的 mapRow方法；
分为【手动映射】与【自动映射】两种方式

1.【手动绑定】手动重写此接口中的 mapRow方法，对自定义类进行包装；
(1)在外部定义一个实现类；
(2)使用匿名内部类；

怎么手动对自定义类进行包装？看以下参考代码即可。
//StudentMapper.java中的内容
public class StudentMapper implements RowMapper<Student> {
   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
      Student student = new Student();

      //先从结果集rs中getXXX拿到这个【该列名下的这个值】
      //然后，调用实例对象中的(暴露出来的public的)setXXX方法，
      //【逐一地】把getXXX到的值 set进实例对象中

      //若ResultSet结果集中有多行数据，则：传入的是单条记录(单行)， 内部n次调用mapRow方法；
      student.setId(rs.getInt("id"));
      student.setName(rs.getString("name"));
      student.setAge(rs.getInt("age"));

      //返回此(setXXX完毕的)封装好的实例对象
      return student;
   }
}
▲ 此StudentMapper是 RowMapper泛型接口 类型的，
后续需将此StudentMapper作为 RowMapper<T> rm形参，传入 query方法中：
query(String sql, Object[] args, RowMapper<T> rm)


2.【自动映射】import org.springframework.jdbc.core.BeanPropertyRowMapper
RowMapper 是一个泛型接口，而 BeanPropertyRowMapper 是它的实现类，
// public class BeanPropertyRowMapper<T> implements RowMapper<T>
可以自动的把数据表的数据映射到对象中（需要对象属性名与数据库的字段名相同）

使用时：BeanPropertyRowMapper 接受一个目标泛型的 Class(类_类型实例对象) 作为构造器参数
如：创建它的实例对象 new BeanPropertyRowMapper<目标类的泛型 如Person>(目标类的泛型 如Person.class)，
可直接将此作为RowMapper<T> rm形参，传入 query方法中，格式如下：

query(
    String sql,
    Object[] args,

    //直接把BeanPropertyRowMapper实例对象 传进来！！
    new BeanPropertyRowMapper<Person>(Person.class)
)


*  */

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List; //结果集以List类型给出
import java.util.Map; //queryForList中，产出的键值对
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import pers.yo.utils.JDBCs.JDBCUtils2;
import pers.yo.adv1.demo28_JDBC.basic.StuMapper; //手动实现的mapRow类

public class Demo07RowMapper1 {
    public static void main(String[] args) {
        JdbcTemplate temp = new JdbcTemplate( JDBCUtils2.getDataSource() ); //创建JdbcTemplate实例对象temp
        String sql1 = " select * from stu where PE=? "; //定义SQL语句

        StuMapper stuMap = new StuMapper();
        /* List<T> query( String sql, 【可选的 -Object[] args】, RowMapper<T>泛型接口类型 rm  )
        * 返回一个 【由查询结果转换为 自定义实例对象】的集合List；
        * 集合List的泛型与自定义实例对象的泛型相同

        * 【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
        * 如 不用在外部new了，直接在此处传入一个 new Object[]{ 15-第一个?号, "user4"-第二个?号 }

        * 当传入形参为RowMapper类型时，系统的处理机制是：逐行转换为RowMapper实例对象。
        *  即 RowMapper方式传入的是单条记录(单行)， 内部n次调用mapRow方法；
        * RowMapper<T>泛型接口类型 rm：  此rm为【RowMapper<T>泛型接口类型 的实现类 的实例对象】
        *  */
        List<Student> list1 = temp.query( sql1, new Object[]{60}, stuMap );
//        List<Student> list1 = temp.query(
//                sql1,
//                new Object[]{60},
//                //直接传入BeanPropertyRowMapper实例对象，记住要写上 目标类型的泛型 和 目标类型的 类_类型实例对象(.class)
//                new BeanPropertyRowMapper<Student>(Student.class)
//        );
        //稍后我将用增强for循环或迭代器，遍历一哈这些查询结果
        System.out.println( "多记录的查询：使用query()，查询PE为60分的人嗷！" );
        for( Student one : list1 ){
            System.out.println( one.toString() ); //使用IDEA为我智能重写的toString方法！
        };
        System.out.println( "-------------------------" );
        /* 上文中，StuMapper stuMap = new StuMapper() 已经定义好了
        * 接下来就直接拿stuMap来用，尝试其他各种查询方法
        *  */
        //还是利用sql1语句来查
        System.out.println( "多记录的查询：使用queryForList()，查询PE为60分的人嗷！" );
        List< Map<String,Object> > list_map = temp.queryForList( sql1, new Object[]{60} );
        //System.out.println( list_map.size() );
        //使用迭代器，遍历list_map，注意迭代器的泛型为 Map<String,Object>
        Iterator< Map<String,Object> > it_listMap = list_map.iterator();
        while( it_listMap.hasNext() ){
            System.out.println( it_listMap.next() );
        };
        System.out.println( "-------------------------" );
        System.out.println( "单记录的查询：使用queryForObject()，查询学号为A02的人" );
        String sql2 = " select * from stu where sno =? ";

        /* 若将sql2更改为 多记录的查询，如select * from stu 则会报错Incorrect result size
        * 所以，queryForObject()只适用于 单条记录的查询
        *  */
        //String sql2 = " select * from stu ";

        /* 这里的返回值可以直接写成 Student o = ... ;
        * 也可以先用 Object o = ... 暂时接收一哈，
        * 然后再向下转型Student s1 = (Student)o ;
        *  */
        Object o = temp.queryForObject(
                sql2,
                new Object[]{"A02"},
                //直接传入BeanPropertyRowMapper<目标类的泛型>(目标类的类_类型 实例对象 -.class)
                new BeanPropertyRowMapper<Student>(Student.class)
                //stuMap //或者使用 手动映射的stuMap
        );
        Student s1 = (Student)o; //向下转型喽！
        System.out.println( s1.toString() ); //康康查询结果
        System.out.println( "-------------------------" );
        System.out.println( "单记录的查询：使用queryForMap，查询学号为A02的人" );
        //还是使用sql2语句
        Map<String,Object> rs_map = temp.queryForMap( sql2, new Object[]{"A02"} );
        /* 返回一个 【由 单条查询结果的各列、值 对应转换而成的 若干个键值对 】 Map集合，
        * 这【若干个】键值对中：键是原表中对应列名column，值是原表中对应列名column对应的值
        * 键值对的数目 等于 select语句中选取的列数(选取几列，就有几个【列-值】，即键值对的数目)
        *  */
        System.out.println( "选取了5列，所以有："+rs_map.size()+" 个键值对" ); //选取了5列，所以此map集合中有5个键值对
        //遍历Map有两种方法：遍历键，或直接遍历 键值对
        Set<String> keys = rs_map.keySet(); //遍历键，用增强for循环或迭代器
        //Set的特点：元素无序，且元素不可重复
        Iterator<String> it_keys = keys.iterator();
        while( it_keys.hasNext() ){
            String oneKey = it_keys.next();//it_keys.next()取出某个键
            /* Map集合中，通过 Object map实例对象.get(Object key) 取该键对应的值，
            * 返回值为Object，可以直接打印输出；
            * 或向下转型为String后输出；
            * 但是！！最好不要向下转型了，可能报错：如原数据为Integer类的，不能直接转为String类！！
            * 报错如下：java.lang.Integer cannot be cast to java.lang.String
            *  */
            Object oneValue = rs_map.get(oneKey);
            System.out.println( oneKey+"="+oneValue );
        };
        System.out.println( "------------------遍历键值对 Set< Map.Entry<String,Object> > set" );
        Set< Map.Entry<String,Object> > kv = rs_map.entrySet(); //获取所有键值对组成的集合Set
        //增强for循环，注意“正在遍历的变量”的数据类型嗷！
        System.out.println( "1.使用增强for循环：" );
        for( Map.Entry<String,Object> one : kv ){ //当前 正在遍历的变量的数据类型为 Map.Entry<String,Object>
            System.out.println( one );
        }
        System.out.println();//空一行
        System.out.println( "2.使用迭代器：" );
        Iterator< Map.Entry<String,Object> > it_kv = kv.iterator(); //在键值对集合上，建立迭代器
        while( it_kv.hasNext() ){
            Map.Entry<String,Object> oneKV = it_kv.next(); //先取出下一个键值对
            String oneKey = oneKV.getKey(); //某个键值对实例对象 Map.Entry<泛型1,泛型2>.getKey() 获取该键值对中的“键”
            Object oneValue = oneKV.getValue(); ////某个键值对实例对象 Map.Entry<泛型1,泛型2>.getValue() 获取该键值对中的“值”
            System.out.println( oneKey+"="+oneValue );
        }
    }
}
