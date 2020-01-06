package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    //接口中全是 public abstract方法
    public abstract User findByUsername( String username ); //根据用户查找用户
    public abstract void save( User user ); //保存用户
    public abstract User findByCode( String code ); //根据激活码查找用户, ——下一步是修改此用户的激活状态为Y
    public abstract void updateStatus( User user ); //修改此用户的激活状态为Y
    public abstract User findByUsernameAndPassword( String username, String password ); //通过用户名和密码 找到某用户
}
