package pers.yo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pers.yo.dao.AccountDao;
import pers.yo.domain.Account;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void run1() throws Exception {
        //---加载配置文件
        InputStream is = Resources.getResourceAsStream( "xmls/SqlMapConfig.xml" );
        //---创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build( is );
        //---通过工厂产出SqlSession
        SqlSession session = factory.openSession();
        //---获取到接口的代理对象
        AccountDao dao = session.getMapper( AccountDao.class );
        //---调用方法
        List<Account> list = dao.findAll();
        for(Account account : list){
            System.out.println(account);
        }
        //---释放资源：后打开的先关闭
        session.close();
        is.close();
    }
}
