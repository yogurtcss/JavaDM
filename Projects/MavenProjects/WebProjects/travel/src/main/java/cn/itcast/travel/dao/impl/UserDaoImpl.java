package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //根据数据库连接池，生成 JdbcTemplate对象来操作数据库
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override //使用IDEA快速生成我需重写的方法
    public User findByUsername(String username) {
        String sql = "select * from tab_user where username=?";
        //查询单条记录的，就用queryForObject
        /* 【坑】不能这样 直接把查询结果赋值为 新的实例对象user！
        会报错 org.springframework.dao.EmptyResultDataAccessException:
        Incorrect result size: expected 1, actual 0

        错误的源代码为：
        User user = template.queryForObject( //直接查询出来的，就是User类型了
                sql,
                new BeanPropertyRowMapper<User>(User.class),
                username
        );

        正确：————以后都要养成这个习惯！
        先新建一个实例对象user，初始化赋值为null，并使用 try...catch写法！
        *  */
        /* 不能这样 直接把数据库查询结果赋值为 新的实例对象user！
        * 正确：————以后都要养成这个习惯！
        * 先实例化一个对象A，初始化赋值为 null
        * 然后在 try...catch...中 把 从数据库中查询而得的“对象”rst 赋值给 A
        *  */
        User user = null; //先实例化一个对象A，初始化赋值为 null
        try{
            //然后在 try...catch...中 把 从数据库中查询而得的“对象”rst 赋值给 A
            user = template.queryForObject( //直接查询出来的，就是User类型了
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    username
            );
        }catch( Exception e ){
            // e.printStackTrace(); //不输出报错信息

            /* 【坑！！】template.queryForObject(...)
            * 若查询为空，则会输出报错信息，
            * 但还是能正常执行的
            *  */
        }

        return user;
    }

    /* 一、普通的【执行 增、删、改】方法，
    1.又细分为：有返回值的int update(...) 与 无返回值的void execute(String sql)
    ▲ 有返回值int的：int update( String sql, [可选的- args… 按sql语句中的?号的先后顺序，一一对应赋值] )，返回值为影响的行数int
    ▲ 无返回值的：void execute( String sql )，直接执行sql语句，【但不能为sql语句中的?号赋值！！】很少用！！
    *  */

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        /* 2020-01-05 22:23:24
        * 这里插入数据，用的是 template.update()方法，我真的是忘了！
        * 这里没有用 实例对象去接收数据库查询出来的值,所以不用 try...catch!!
        *  */
        template.update(  sql, //要执行的sql语句
                user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(),      user.getTelephone(),
                user.getEmail(),    user.getStatus(),   user.getCode()
        );
    }

    @Override
    public User findByCode(String code) {
        /* 每次接收数据库查询的结果时:
        * 务必提前初始化一个实例对象A为null
        * 在try...catch...中 把查询结果赋给 A! 这样就安全了!
        *  */
        User user = null; //提前初始化一个实例对象user 为null
        try{
            String sql = "select * from tab_user where code=?";
            user = template.queryForObject( //查询单条记录
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    code
            );
        }catch( DataAccessException e){
            //DataAccessException 是 RuntimeException, 是一个无须检测的异常，不要求代码去处理这类异常
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateStatus(User user) {
        /* 这里没有用 实例对象去接收数据库查询出来的值,所以不用 try...catch!!
        * 若sql语句是拼接而成的, 注意拼接处的末尾要加空格 ！！
        *  */
        String sql = "update tab_user set status='Y' where uid=?";
        template.update( sql, user.getUid() ); //普通的【执行 增、删、改】方法：都是用这个方法嗷
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        /* 每次接收数据库查询结果时：
        * 务必事先初始化一个实例对象A 为 null
        * 然后在 try...catch...中 把数据库查询结果 赋给 A ！！
        *  */
        User user = null;
        try{
            String sql = "select * from tab_user where username=? and password=?";
            user = template.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(), user.getPassword()
            );
        }catch( Exception e ){
            e.printStackTrace();
        }

        return user;
    }
}
