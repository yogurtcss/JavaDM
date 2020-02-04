package pers.yo.reactspringboot2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.reactspringboot2.dao.UserDao;
import pers.yo.reactspringboot2.domain.User;
import pers.yo.reactspringboot2.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
}
