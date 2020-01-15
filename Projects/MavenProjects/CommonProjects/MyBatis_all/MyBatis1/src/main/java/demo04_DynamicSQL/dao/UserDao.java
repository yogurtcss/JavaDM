package demo04_DynamicSQL.dao;

import demo04_DynamicSQL.domain.QueryVo;
import demo04_DynamicSQL.domain.User;

import java.util.List;

public interface UserDao {
    public abstract List<User> findAll();
    public abstract User findById( Integer id );
    public abstract List<User> findByName( String username ); //根据名称模糊查询用户信息
    public abstract List<User> findUserByVo( QueryVo vo ); //根据queryVo中的条件查询用户

    //：user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能是都有
    public abstract List<User> findUserByCondition( User user );

    public abstract List<User> findUserInIds( QueryVo vo ); //根据queryvo中提供的id集合，查询用户信息
}
