package pers.yo.study.Demo01_Request.example.dao;

/* DAO（Data Access Object）
一个为数据库或其他持久化机制提供了抽象接口的对象，
在不暴露底层持久化方案实现细节的前提下提供了各种数据访问操作。

DAO 模式实际上包含了两个模式，
一是 Data Accessor（数据访问器），解决如何访问数据的问题
二是 Data Object（数据对象），解决如何用对象封装数据。

---------------------------------------------------------
UserDAO 【操作数据库db中 表user的数据】 的类
将来对 【此表user】的 增删改查方法，都放入这个类中

*  */

/* 引入 自定义的 待封装好的User类 —— 这是用户的实体类
* 从数据库db1下的表user取出的数据：
* id、username、password 被封装为User实例对象的成员属性
*  */
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.yo.study.Demo01_Request.example.domain.User;
import pers.yo.study.Demo01_Request.example.util.JDBCUtils;

public class UserDAO {
    /* Spring框架对JDBC的简单封装。提供了一个JDBCTemplate对象简化JDBC的开发
    * 创建JdbcTemplate对象。依赖于数据源DataSource
    * JdbcTemplate temp = new JdbcTemplate( ds数据库连接池对象 );
    *
    * 不用另外使用preparedStatement了，直接定义sql语句，传给temp即可！！
    * 不需手动申请连接，也不需手动释放资源，JdbcTemplate自动做了这些工作；
    * 我们只需关心如何定义sql语句，如何执行sql语句，以及如何处理结果。
    *  */
    private JdbcTemplate temp = new JdbcTemplate( JDBCUtils.getDataSource() );


    /**
     * 登录方法
     * @param userTryLogin 尝试登陆的用户，只有用户名和密码
     * @return user包含全部的用户数据
     */
    public User login( User userTryLogin ){
        try{
            // String sql = " select ?, ? from user ";
            String sql = "select * from user where username=? and password=?";

            /* 调用temp中的queryForObject()方法：【单条记录的查询语句】，返回值也是单个的
            * queryForObject(
                     String sql,
                     Object[] args,

                     //直接把BeanPropertyRowMapper实例对象 传进来！！
                     new BeanPropertyRowMapper<Person>(Person.class)
                    ) //我踏马还真忘了怎么写！
            *  */

            User user = temp.queryForObject(
                    sql,
                    //我佛了，这里的getPassword()写错了，之前写成了 getUsername！！
                    new Object[]{ userTryLogin.getUsername(), userTryLogin.getPassword() },
                    new BeanPropertyRowMapper<User>(User.class)
            );
            return user;

//            老师用的是以下的另一个queryForObject()重载方法啦！
//            temp.queryForObject(
//                    sql,
//                    RowMapper<T>  rowMapper,
//                    Object ...args   //可变参数，不是数组形式的，就放在第三个位置上
//            )

        }catch( DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

}
