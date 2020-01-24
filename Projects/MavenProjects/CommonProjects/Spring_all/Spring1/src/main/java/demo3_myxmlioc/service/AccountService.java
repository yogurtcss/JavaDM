package demo3_myxmlioc.service;

import demo3_myxmlioc.domain.Account;

import java.util.List;

public interface AccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    //2020-01-21 10:03:43
    public abstract void transfer(String sourceName,String targetName,Float money);

    /* 2020-01-24 11:54:18
    * test()方法只是连接点，而不是切入点
    * 因为test()方法没有被增强
    *  */
    //public abstract void test();
}
