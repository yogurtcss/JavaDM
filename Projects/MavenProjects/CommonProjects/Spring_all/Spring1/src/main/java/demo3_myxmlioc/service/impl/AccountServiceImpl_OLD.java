package demo3_myxmlioc.service.impl;

import demo3_myxmlioc.dao.AccountDao;
import demo3_myxmlioc.domain.Account;
import demo3_myxmlioc.service.AccountService;
import demo3_myxmlioc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("accountServiceImpl")
public class AccountServiceImpl_OLD implements AccountService {
    //@Autowired
    private AccountDao aDao;

    //2020-01-21 10:06:08
    private TransactionManager tsManager;

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    //加了@Autowired关键字后，set方法就不是必需的了
    public void setaDao(AccountDao aDao) {
        this.aDao = aDao;
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> list = null;
        try{
            //---1.开启事务，我已在获取连接对象时，禁止自动提交！
            tsManager.beginTransaction();
            //---2.执行操作
            list = aDao.findAllAccount();
            //---3.手动提交事务
            tsManager.commit();
        }catch( Exception e ){
            //---4.遇到异常，回滚
            tsManager.rollback();
            e.printStackTrace();
        }finally{
            ///---5.最后释放 资源
            tsManager.release();
        }
        //--- 返回结果
        return list;
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

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

    }
}
