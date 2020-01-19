package demo2_annoioc.ui;


import demo2_annoioc.dao.AccountDao;
import demo2_annoioc.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        //---1.创建Spring的应用上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext( "props/bean_annoioc.xml" ); //接口回调，向上转型
        //---2.从Spring的应用上下文中获取bean
        AccountService as = (AccountService)ac.getBean("accountService");
        System.out.println( "as："+as );
        //AccountDao aDao1 = (AccountDao)ac.getBean("accountDao1");
        AccountDao aDao1 = ac.getBean( "accountDao1", AccountDao.class );
        System.out.println( "aDao1："+aDao1 );
        as.saveAccount();
    }
}
