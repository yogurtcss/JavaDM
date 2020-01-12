package pers.yo.mybatis1.dao;

import pers.yo.mybatis1.domain.User;

import java.util.List;

//用户的持久层接口 UserDao.java
public interface UserDao {
    //查询所有数据
    public abstract List<User> findAll();
}
