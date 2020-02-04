package pers.yo.reactspringboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pers.yo.reactspringboot2.domain.User;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    public abstract List<User> findAll();
}
