package demo02.test;

import demo02_MyDesign.dao.UserDao;
import demo02_MyDesign.domain.User;
import demo02_MyDesign.mybatis.io.Resources;
import demo02_MyDesign.mybatis.sqlsession.SqlSession;
import demo02_MyDesign.mybatis.sqlsession.SqlSessionFactory;
import demo02_MyDesign.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream( "SqlMapConfig.xml" );
        //2.创建 SqlSessionFactory 的构建者builder，等待创建真正的工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.真正创建工厂
        SqlSessionFactory factory = builder.build( is );
        //4.利用工厂创建一个 数据库会话
        SqlSession session = factory.openSession();
        //5.利用此数据库会话，创建持久层接口dao的代理对象
        // 注：此代理对象得到的 “增强”之处 ——就是调用此持久层接口dao某方法嗷！
        UserDao userDao = session.getMapper( UserDao.class );
        //6.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for( User one : users ){
            System.out.println( one );
        }
        //7.释放资源
        session.close();
        is.close();
    }
}
