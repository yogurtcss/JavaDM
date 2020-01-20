package demo3_myxmlioc.service.impl;

import demo3_myxmlioc.dao.AccountDao;
import demo3_myxmlioc.domain.Account;
import demo3_myxmlioc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao aDao;

    //加了@Autowired关键字后，set方法就不是必需的了
    public void setaDao(AccountDao aDao) {
        this.aDao = aDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return( aDao.findAllAccount() );
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return( aDao.findAccountById(accountId) );
    }

    @Override
    public void saveAccount(Account account) {
        aDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        aDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        aDao.deleteAccount(accountId);
    }
}
