package demo1.service.impl;

import demo1.dao.AccountDao;
import demo1.dao.impl.AccountDaoImpl;
import demo1.factory.BeanFactory;
import demo1.service.AccountService;

public class AccountServiceImpl implements AccountService {

    //private AccountDao dao = new AccountDaoImpl();  //这样做是合法的
    //private AccountDao dao = (AccountDao)BeanFactory.getBean( "accountDao" );  //报错，空指针异常
    //private AccountDao dao;

    private AccountDao dao = (AccountDao) BeanFactory.getBean("accountDao");
    /* 2020-01-18 17:23:16
    * 之前的例子：
    * 在 UserDaoImpl中：private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    * 在 UserServiceImpl中：private UserDao dao = new UserDaoImpl();
    * ∴ 用new关键字 正射出来的 private私有变量 是在本类中正常使用的
    *
    * 而在这里，用 工厂类BeanFactory 反射出来的 private私有变量，在本类中是 null……
    *
    *  */

    //private AccountDao dao = (AccountDao)Class.forName( "demo1.dao.impl.AccountDaoImpl" ).newInstance();
    public AccountServiceImpl() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    }

    @Override
    public void saveAccount() {
        //dao = (AccountDao)BeanFactory.getBean( "accountDao" );
        System.out.println( dao );
        dao.saveAccount();
    }
}
