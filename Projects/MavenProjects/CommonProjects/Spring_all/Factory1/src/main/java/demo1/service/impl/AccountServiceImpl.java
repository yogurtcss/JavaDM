package demo1.service.impl;

import demo1.dao.AccountDao;
import demo1.dao.impl.AccountDaoImpl;
import demo1.factory.BeanFactory;
import demo1.service.AccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountServiceImpl implements AccountService {

    //private AccountDao dao = new AccountDaoImpl();  //这样做是合法的
    private AccountDao dao = (AccountDao)BeanFactory.getBean( "accountDao" );  //报错，空指针异常，说明只能通过 动态代理创建接口实现类
    //private AccountDao dao;

    public void saveAccount() {
        //dao = (AccountDao)BeanFactory.getBean( "accountDao" );
        System.out.println( "我是真正saveAccount()中的方法！" );
        dao.saveAccount();
    }

    public void test(){
        //dao = (AccountDao)BeanFactory.getBean("accountDao");
        System.out.println( "------------------" );
        System.out.println( "我是test()中的方法！" );
        dao.saveAccount();
    }

}
