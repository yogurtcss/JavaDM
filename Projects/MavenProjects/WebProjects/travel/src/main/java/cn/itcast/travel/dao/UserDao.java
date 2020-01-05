package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    //接口中全是 public abstract方法
    public abstract User findByUsername( String username );
    public abstract void save( User user );
}
