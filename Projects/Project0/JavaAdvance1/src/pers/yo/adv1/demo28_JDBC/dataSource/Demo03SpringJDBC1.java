package pers.yo.adv1.demo28_JDBC.dataSource;

/* Spring JDBC
Spring框架对JDBC的简单封装，提供了一个JdbcTemplate对象，简化JDBC的开发

步骤：
1.导入spring系的Jar包(导入至 libs项目依赖文件夹下；与数据库驱动jar包放在同一目录下)
2.以数据源DataSource实例对象ds为形参，创建JdbcTemplate实例对象
JdbcTemplate temp = new JdbcTemplate(数据源DataSource实例对象ds)

3.调用JdbcTemplate实例对象temp中已有方法，来实现 “CRUD”，以下介绍常用方法
--------------------
注：1.前置语句String sql = " 此sql语句中可能有多个问号 ??? 等待赋值  "
2.【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值

3.仅queryForMap()用的形参：[可选的- args...可变参数] 意为： 按sql语句中的?号的先后顺序，一一对应赋值

其余方法都用的形参是 【可选的 -Object[] args】传入Object类型的数组
--------------------

一、普通的【执行 增、删、改】方法，
1.又细分为：有返回值的int update(...) 与 无返回值的void execute(String sql)
▲ 有返回值int的：int update( String sql, [可选的- args… 按sql语句中的?号的先后顺序，一一对应赋值] )，返回值为影响的行数int
▲ 无返回值的：void execute( String sql )，直接执行sql语句，【但不能为sql语句中的?号赋值！！】很少用！！

2.批量执行sql语句
void batchUpdate( new String[]{sql语句}- 传入形参为 若干条sql语句的 字符串数组 )

--------------------
spring 3.2.2 版本之后：取消了jdbcTemplate.queryForInt() 和 jdbcTemplate.queryForLong() 方法
全部用 queryForObject() 代替了，这里就不详解了。
--------------------

二、查询
1.【多条记录的查询语句】 即返回 >=0 条数据
▲1  List<T> query( String sql, 【可选的 -Object[] args】, RowMapper<T>泛型接口类型 rm  )
返回一个 【由查询结果转换为 自定义实例对象】的集合List；
集合List的泛型与自定义实例对象的泛型相同

* 【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
   如 不用在外部new了，直接在此处传入一个 new Object[]{ 15-第一个?号, "user4"-第二个?号 }

* 当传入形参为RowMapper类型时，系统的处理机制是：逐行转换为RowMapper实例对象。
  即 RowMapper方式传入的是单条记录(单行)， 内部n次调用mapRow方法；
* RowMapper<T>泛型接口类型 rm：  此rm为【RowMapper<T>泛型接口类型 的实现类 的实例对象】

▲2  List< Map<String键, Object值> >   queryForList( String sql, 【可选的 -Object[] args】,  )
返回一个 【装有若干个Map(集合)对象】 的 集合List；
* 细节：将查询结果中 每一条记录封装为一个Map集合，再将Map集合装载到List集合中；
(Map集合中 每一个键值对：键为原表中列名column，值为原表中列名column对应的值)
* 【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
   如 不用在外部new了，直接在此处传入一个 new Object[]{ 15-第一个?号, "user4"-第二个?号 }


--------------------
2.【单条记录的查询语句】，返回值也是单个的。
queryForObject()与queryForMap()

--------------------
▲1  Object  queryForObject(sql语句, 【可选的 -Object[] args】, Class类类型<T> requiredType, )
返回一个【基本数据类型 或String】的实例对象——可认为是 【返回一个“值”的 包装类实例对象】
注：聚合函数的查询结果通常为一个“值”；
故queryForObject()方法 常用于聚合函数的查询：count、avg、sum、max、min

聚合函数：将一列数据作为一个整体，进行纵向的计算
 count 计算个数
 max
 min
 sum
 avg 计算平均值

* sql：即sql语句
* 【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
   如 不用在外部new了，直接在此处传入一个 new Object[]{ 15-第一个?号, "user4"-第二个?号 }

* Class类类型<T> requiredType：
  (此语句执行后的返回值)-等号左边、返回的实例对象所属的“类” 的【类_类型实例对象】
  如 返回的实例对象是整型int的，则它所属的“类”为Integer，所以 此处的requiredType为 Integer.class

▲2  Object queryForObject( String sql, 【可选的 -Object[] args】, RowMapper<T>泛型接口类型 rm )
(仅仅)返回一个 【由 单条查询结果 转换而成的 (一个)自定义实例对象】
* sql与【可选的 -Object[] args】的解释同理
* 自定义实例对象，如自定义的Person类的实例对象
* RowMapper<T>泛型接口类型 rm：  此rm为【RowMapper<T>泛型接口类型 的实现类 的实例对象】
--------------------

▲3  Map<String, Object> queryForMap( String sql, 【可选的-Object...args可变参数】 )
返回一个 【由 单条查询结果的各列、值 对应转换而成的 若干个键值对 】 Map集合，
这【若干个】键值对中：键是原表中对应列名column，值是原表中对应列名column对应的值
键值对的数目 等于 select语句中选取的列数(选取几列，就有几个【列-值】，即键值对的数目)

注：【可选的-Object...args可变参数】不是传Object []数组了，而是：直接为原sql语句中各 ? 号按先后顺序赋值！！


----------------------------------------------------------
▲ 感觉List和Set是 死对头
List 的特点是【元素有序】、【元素可重复】。
Set 的特点是【元素无序】，【而且元素不可重复】。

▲ Map接口 将键映射到值的接口，每个键key最多只能映射一个值value
----------------------------------------------------------
*  */

import org.springframework.jdbc.core.JdbcTemplate; //名字好长
import pers.yo.utils.JDBCs.JDBCUtils2;

public class Demo03SpringJDBC1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JdbcTemplate实例对象
        JdbcTemplate temp = new JdbcTemplate( JDBCUtils2.getDataSource() );
        //3.定义sql语句，准备调用方法
        String sql = "update stu set sname=? where sno=?";
        //4.调用方法，如update方法的返回值为int 影响的行数
        int count = temp.update( sql, "JdbcTemp", "A02" );
        /* 不需手动申请连接，也不需手动释放资源，JdbcTemplate自动做了这些工作；
        * 我们只需关心如何定义sql语句，如何执行sql语句，以及如何处理结果。
        *  */
        System.out.println( count ); //打印影响的行数
    }
}
