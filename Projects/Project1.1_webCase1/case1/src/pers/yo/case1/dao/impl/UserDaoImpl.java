package pers.yo.case1.dao.impl;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.yo.case1.dao.UserDao;
import pers.yo.case1.domain.User;
import pers.yo.case1.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        /* 1.定义sql
        * 注意，sql语句中插入的值要对应各列
        * 在我自定义的user表中，各列按顺序为
        * id、username、password、name、gender、age、address、qq、email
        *  */
        String sql = "insert into user values(null,null,null,?,?,?,?,?,?)";
        //2.执行sql：填充后6个问号嗷!
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        /* 2019-12-25 16:39:21
        * 不能把后面的“分页查询”语句 给删了
        * 因为 list.jsp中已经写有分页查询的语句了，
        * 若删了，list.jsp就显示不出数据了！
        *
        * 所以，学到哪个功能时：先删掉原有代码，自己再重写一遍，不要冲动全删了！！
        *  */
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id=?";
        /* 通过 queryForObject( sql语句，  RowMapper<> rm,  ...args单个的可变参数-在第三位的  );
        *
        * 第二种重载方法：
        * queryForObject( sql语句,
        *                Object[] args,    -Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
        *                RowMapper<> rm )
        *  */
        User user = (User)template.queryForObject(
                sql,
                new BeanPropertyRowMapper<User>(User.class),
                id
        );
        return user;
    }

    @Override
    public void update(User user) {
        /* 一想到 update语句，突然有点懵
        再看看这个正确的写法！！
        update user
            set name="superbaby", gender="男", age=10, address="中国", qq="111", email="111@qqq.com"
        where id="1";
        *  */

        /* BeanUtils.populate( user, map );
        * 注意，map中就有 隐藏域id
        * 此时 post表单提交的【隐藏域id】已经被填充进用户user实例对象中，在sql语句中可直接从此user实例对象中取出id值
        * 还有 用户提交的表单数据没有 username和password！！
        *  */

        /* 花了一个小时来排这个BUG：2019-12-26 20:01:42
        * 正确的sql语句为：
        * update user set name=?, gender=?, age=?, address=?, qq=?, email=? where id=?
        * 要有空格！
        *
        * 最初的写法
        * String sql = "update user" +
                     "set name=?, gender=?, age=?, address=?, qq=?, email=?" +
                     "where id=?";
        *
        * System.out.println( sql );
        * //输出的结果是 update userset name=?, gender=?, age=?, address=?, qq=?, email=?where id=?
        * 拼串后：user和set之间没有空格了，email=? 与where也没有空格了！
        * 这样的sql语句当然无法执行！
        *
        * 所以，拼串的sql语句，在【拼接处末尾】一定要注意空格！
        * "update user -(拼接处末尾加一个空格)"
        * "set ...., email=? -(拼接处末尾加一个空格)"
        *
        * 或者直接一条sql语句完整写完它：
        * String sql2 = "update user set name=?, gender=?, age=?, address=?, qq=?, email=? where id=? " ;
        * 如果一条地写完sql语句，不会出现上述的 空格问题！
        *  */

        //所以，拼串的sql语句，在【拼接处末尾】一定要注意空格！
        String sql = "update user " +
                     "set name=?, gender=?, age=?, address=?, qq=?, email=? " +
                     "where id=? ";

        System.out.println( sql ); //查看拼接的sql语句是否是正确的：拼串的sql语句，在【拼接处末尾】一定要注意空格

        //执行更新操作
        template.update(
                sql,
                user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),
                //此时 post表单提交的【隐藏域id】已经被填充进用户user实例对象中，在sql语句中可直接从此user实例对象中取出id值
                user.getId()
        );

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
