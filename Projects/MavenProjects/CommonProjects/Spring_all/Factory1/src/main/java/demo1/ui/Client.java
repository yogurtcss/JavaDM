package demo1.ui;

import demo1.dao.AccountDao;
import demo1.factory.BeanFactory;
import demo1.service.AccountService;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        for( int i=0; i<4; i++ ){
            //通过BeanFactory工厂，创建服务层实例对象
            AccountService service = (AccountService) BeanFactory.getBean("accountService");
            service.saveAccount();

            //以下正常运行
//            AccountDao dao = (AccountDao) BeanFactory.getBean("accountDao");
//            dao.saveAccount();
        }
    }
}
