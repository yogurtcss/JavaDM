package demo3_myxmlioc.service.impl;

import demo3_myxmlioc.dao.AccountDao;
import demo3_myxmlioc.domain.Account;
import demo3_myxmlioc.service.AccountService;
import demo3_myxmlioc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class AccountServiceImpl_OLD implements AccountService {
    private AccountDao aDao;

    //2020-01-21 10:06:08   --在xml配置中，还是要写上这个<property />属性的
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
        Account a = null;
        try{
            tsManager.beginTransaction(); //开启事务
            a = aDao.findAccountById( accountId ); //处理结果
            tsManager.commit(); //提交事务
        }catch( Exception e ){
            tsManager.rollback(); //回滚事务
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void saveAccount(Account account) {
        try{
            tsManager.beginTransaction();
            aDao.saveAccount( account );
            tsManager.commit();
        }catch( Exception e ){
            tsManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            tsManager.beginTransaction();
            aDao.updateAccount( account );
            tsManager.commit();
        }catch( Exception e ){
            tsManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try{
            tsManager.beginTransaction();
            aDao.deleteAccount( accountId );
            tsManager.commit();
        }catch( Exception e ){
            tsManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

        ////使用了 service代理对象之前：在这里进行事务操作
//        try{
//            tsManager.beginTransaction();
//            //原本的问题：每调用一次aDao，都会从连接池中取一个新的连接
//            Account source = aDao.findAccountByName( sourceName ); //转出者
//            Account target = aDao.findAccountByName( targetName ); //转入者
//            source.setMoney( source.getMoney()-money ); //转出者 减钱
//            target.setMoney( target.getMoney()+money ); //转入者 加钱
//            aDao.updateAccount( source ); //更新转出者的信息
//
//            //int i = 1/0; //手动制造异常
//
//            aDao.updateAccount( target ); //更新转入者的信息
//            tsManager.commit(); //提交事务
//        }catch( Exception e ){
//            tsManager.rollback(); //回滚
//            e.printStackTrace();
//        }finally{
//            tsManager.release();
//        }


        //使用了 service代理对象后，更改为如下代码
        System.out.println("transfer....");
        //2.1根据名称查询转出账户
        Account source = aDao.findAccountByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = aDao.findAccountByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5更新转出账户
        aDao.updateAccount(source);

        int i = 1/0; //手动制造异常

        //2.6更新转入账户
        aDao.updateAccount(target);

    }
}