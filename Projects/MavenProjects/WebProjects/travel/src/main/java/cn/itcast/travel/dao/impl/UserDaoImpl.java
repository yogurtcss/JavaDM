package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //根据数据库连接池，生成 JdbcTemplate对象来操作数据库
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override //使用IDEA快速生成我需重写的方法
    public User findByUsername(String username) {
        String sql = "select * from tab_user where username=?";
        //查询单条记录的，就用queryForObject
        User user = template.queryForObject( //直接查询出来的，就是User类型了
                sql,
                new BeanPropertyRowMapper<User>(User.class),
                username
        );
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
        *  */
        template.update(  sql, //要执行的sql语句
                user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(),      user.getTelephone(),
                user.getEmail(),    user.getStatus(),   user.getCode()
        );
    }
}
