package demo05_Query.dao;

import demo05_Query.domain.Account;
import demo05_Query.domain.AccountUser;

import java.util.List;

public interface AccountDao {

    /* 查询所有账户，同时还要获取到当前账户的所属用户信息
    * 返回值 list集合中的【泛型Account类——就包含着所属用户的信息，因为一个account对应一个user，所以account类中要有一个user】
    *  */
    public abstract List<Account> findAll();

    /* 查询所有账户，并且带有用户名称和地址信息
    *  */
    public abstract List<AccountUser> findAllAccount();
}
