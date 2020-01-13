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
        /* 2020-01-13 21:19:43
        * 读取配置文件时：默认是从 resources的总目录下 进行读取的
        * 若我把SqlMapConfig_demo2.xml 放在 resources总目录/ props文件夹/ SqlMapConfig_xml文件夹  下
        * 看看能否读取到此配置文件SqlMapConfig_demo2.xml
        *     ——【已验证】，可以正确读取此SqlMapConfig_demo2.xml配置文件
        *
        * 此时的相对路径为：props/SqlMapConfig_xml/SqlMapConfig_demo2.xml
        * 注意，开头没有斜杠！！
        *  */

        //把SqlMapConfig.xml 放在 resources总目录下时，直接写文件名即可
        //InputStream is = Resources.getResourceAsStream( "SqlMapConfig.xml" );

        //相对路径，是以 resources总目录 为基准，进行寻找配置文件嗷
        //注意，开头没有斜杠！！
        InputStream is = Resources.getResourceAsStream( "props/SqlMapConfig_xml/SqlMapConfig_demo2.xml" );
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
