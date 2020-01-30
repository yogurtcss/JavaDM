/* 二、测试
* java/test包/ TestSpring.java
*  */

package pers.yo.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.yo.service.AccountService;

public class TestSpring {

    @Test
    public void run1(){
        //---1.加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext( "classpath:xmls/applicationContext.xml" );
        //---2.获取对象
        /* 获取哪个对象？加了@Service注解
        @Service( "accountService" )
        public class AccountServiceImpl implements AccountService
        *  */
        AccountService as = ac.getBean( "accountService", AccountService.class );
        //---3.调用方法
        as.findAll();
        //---当test测试通过时，说明Spring环境搭建成功了
    }
}