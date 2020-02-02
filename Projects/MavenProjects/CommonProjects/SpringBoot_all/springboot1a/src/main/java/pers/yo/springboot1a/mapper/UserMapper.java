package pers.yo.springboot1a.mapper;


import org.apache.ibatis.annotations.Mapper;
import pers.yo.springboot1a.domain.User;

import java.util.List;


/* @Mapper标记该类是一个mybatis的mapper接口，
* 可以被spring boot自动扫描到spring上下文中
* */
@Mapper
public interface UserMapper {
    public abstract List<User> queryUserList();
}
