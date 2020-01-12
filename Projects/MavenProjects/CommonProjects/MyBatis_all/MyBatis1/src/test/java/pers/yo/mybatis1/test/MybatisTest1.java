package pers.yo.mybatis1.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pers.yo.mybatis1.dao.UserDao;
import pers.yo.mybatis1.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest1 {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream( "SqlMapConfig.xml" );
        //2.创建sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build( is );
        //3.使用工厂生产 SqlSession实例对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的 代理对象
        UserDao dao = session.getMapper( UserDao.class );
        //5.使用代理对象执行方法
        List<User> users = dao.findAll();
        for( User one : users ){
            System.out.println( one );
        }
        //6.释放资源：后打开的先关闭
        session.close();
        is.close();

    }
}
