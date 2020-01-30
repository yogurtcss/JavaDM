package pers.yo.service.impl;

import org.springframework.stereotype.Service;
import pers.yo.domain.Account;
import pers.yo.service.AccountService;

import java.util.List;

@Service( "accountService" )
public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户...");
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存帐户...");

    }
}
