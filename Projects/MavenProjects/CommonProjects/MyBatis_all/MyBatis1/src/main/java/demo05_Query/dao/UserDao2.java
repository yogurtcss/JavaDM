package demo05_Query.dao;

import demo05_Query.domain.User;

import java.util.List;

public interface UserDao2 {
    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    public abstract List<User> findAll();


    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    public abstract User findById(Integer userId);
}
