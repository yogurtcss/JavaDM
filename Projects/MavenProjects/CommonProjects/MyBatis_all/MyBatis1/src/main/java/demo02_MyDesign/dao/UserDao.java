package demo02_MyDesign.dao;

import demo02_MyDesign.domain.User;
import demo02_MyDesign.mybatis.annotation.Select; //引入自定义的注解

import java.util.List;

public interface UserDao {
    //@Select( "select * from user" ) //使用注解方式配置MyBatis
    public abstract List<User> findAll();
}
