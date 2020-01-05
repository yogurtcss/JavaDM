package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl(); //接口回调，向上转型

    @Override //使用IDEA快速生成需重写的方法
    public boolean regist(User user) {
        /* 调用dao，根据用户名查询用户
        * userFromSQL 可能存在的用户
        *  */
        User userFromSQL = dao.findByUsername( user.getUsername() );

        return false;
    }

    @Override
    public boolean active(String code) {
        return false;
    }

    @Override
    public User login(User user) {
        return null;
    }
}
