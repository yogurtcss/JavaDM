package pers.yo.mybatis1.dao;

import org.apache.ibatis.annotations.Select;
import pers.yo.mybatis1.domain.User;

import java.util.List;

//用户的持久层接口 UserDao.java
public interface UserDao {
    //查询所有数据
    @Select( "select * from user" ) //在持久层接口中，对接口中的某个方法添加注解
    public abstract List<User> findAll();
}
