package pers.yo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.dao.AccountDao;
import pers.yo.domain.Account;
import pers.yo.service.AccountService;

import java.util.List;

@Service( "accountService" )
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao dao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户...");
        return( dao.findAll() ); //让spring的IOC容器自动注入后，调用dao层的方法
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存帐户...");
        dao.saveAccount(account); //让spring的IOC容器自动注入后，调用dao层的方法
    }
}
