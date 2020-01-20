package demo3_myxmlioc.ui;

import demo3_myxmlioc.domain.Account;
import demo3_myxmlioc.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        //---1.获取核心容器 应用上下文ApplicationContext
        //xml配置写法
        //ApplicationContext ac = new ClassPathXmlApplicationContext( "props/bean_demo3_xmlioc.xml" );
        //注解写法
        ApplicationContext ac = new ClassPathXmlApplicationContext("props/bean_demo3_annoioc.xml");
        AccountService as = ac.getBean( "accountServiceImpl", AccountService.class );
        System.out.println( "as.findAllAccount()方法："+as.findAllAccount() );
        System.out.println( "as.findAccountById()方法："+as.findAccountById(1) );

        /* Java 默认浮点型是double
        * 如果要指明是 float, 须在后面加 小写f， 如 1.0f
        * 或在前面加括号(float)，                如 (float)1.0
        *  */
//        as.saveAccount( new Account(4,"哈哈", 2.33f) ); //注意float的写法！ 2.33f
//        as.saveAccount( new Account(5,"嗯嗯", (float)1.233) ); //注意float的写法！ (float)1.233

//        as.updateAccount( new Account(4,"冲啊！",3838.3f) );

//        as.deleteAccount(5);
//        as.deleteAccount(4);
    }
}
