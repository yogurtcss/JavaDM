package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    public abstract boolean regist( User user ); //注册用户

    public abstract boolean active( String code );
    public abstract User login( User user );
}
