package demo4_springAOP1;

import demo4_springAOP1.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        //---1.获取 应用上下文
        //ApplicationContext ac = new ClassPathXmlApplicationContext("xmls/bean_demo4_springAOP1_xml.xml");
        // 使用 注解 AOP 的配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext( "xmls/bean_demo4_springAOP1_anno.xml" );
        //---2.获取对象
        AccountService as = (AccountService)ac.getBean( "accountService" ); //接口回调，向上转型
        //AccountService as1 = ac.getBean( "accountService", AccountService.class ); //不用向下转型
        //---3.调用方法
//        as.saveAccount();
//        as.deleteAccount();
        as.updateAccount(1);
    }
}