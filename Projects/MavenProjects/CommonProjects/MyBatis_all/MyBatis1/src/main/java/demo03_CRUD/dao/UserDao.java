package demo03_CRUD.dao;

import demo03_CRUD.domain.QueryVo;
import demo03_CRUD.domain.User;

import java.util.List;

public interface UserDao {
    public abstract List<User> findAll(); //查询所有用户
    public abstract void saveUser( User user ); //保存用户，传入形参为 User实例对象

    public abstract void updateUser( User user ); //更新用户
    public abstract void deleteUser( int userId ); //删除用户
    public abstract User findById( int userId ); //根据ID查询用户信息
    public abstract List<User> findByName( String userName ); //根据用户名查找
    public abstract int findTotal(); //查询总用户数量
    public abstract List<User> findUserByVo( QueryVo vo ); //根据queryVo中的条件来查询用户

    /* 2020-01-14 19:51:52
    QueryVo.java中：

    ViewObject 表现层对象，简称 VO对象

    POJO普通对象中的属性(成员变量)一般为 基本数据类型，
    而VO表现层对象中的属性(成员变量) 可以是引用类型( --成员变量是 某个实例对象)

    ▲ 当查询条件中有多个固定数量的查询条件，可以通过传入一个VO对象来进行操作，
    写法如：parameterType="cn.it.pojo.QueryVo--VO的全类名"

    ▲ sql语句中的传参：来自 VO属性(-这是一个对象) 的属性(-这终于是基本数据类型了)
    写法如：#{user.sex}
    --第一层 #{user}      表示getUser ——在VO对象中，get到User这个属性(-这是一个对象)；
    --第二层 #{user.sex}  表示取到User对象后，继续getSex ——在User对象中，get到Sex这个属性(-这终于是基本数据类型了)

    #{user.username}也是同理分析的

    *  */

}
