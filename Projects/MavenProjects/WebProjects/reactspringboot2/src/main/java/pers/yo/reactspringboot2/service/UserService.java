package pers.yo.reactspringboot2.service;

import pers.yo.reactspringboot2.domain.User;

import java.util.List;

public interface UserService {
    public abstract List<User> findAll();
}
