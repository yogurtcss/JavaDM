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

/* 2020-01-12 20:32:36
* 此 MybatisTest1.java 解释了自定义MyBatis框架的细节，如注释所说，共有7步；
* 了解mybatis 内部是怎么执行的，在以后的开发中能更好的使用 mybatis 框架，
* 同时对它的设计理念（设计模式）有一个认识。
*  */

public class MybatisTest1 {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        /* 读取时，可使用
        (1)类加载器：只能读取类路径classpath下的配置文件
        (2)ServletContext对象的 getRealPath()
        *  */
        InputStream is = Resources.getResourceAsStream( "SqlMapConfig.xml" );

        //2.创建SqlSessionFactory工厂的【构建者对象】
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        /* 创建SqlSessionFactory实例对象 工厂factory时，
        使用了 构建者模式 ——即 隐藏【对象的创建细节】。让使用者直接调用方法即可拿到对象
        *  */
        //3.使用构建者对象，创建工厂对象SqlSessionFactory
        SqlSessionFactory factory = builder.build( is );  //builder就是构建者

        //4.使用工厂生产 SqlSession实例对象
        /* 创建SqlSession实例对象session时，
        使用了 工厂模式 ——解耦，降低类之间的依赖关系
        *  */
        SqlSession session = factory.openSession();

        //5.使用SqlSession创建Dao接口的 代理对象
        /* 使用SqlSession创建Dao接口的 代理对象时，
        使用了 代理模式 ——即 不修改源码的基础上，增强已有的方法
        *  */
        UserDao dao = session.getMapper( UserDao.class );

        //6.使用代理对象执行方法
        List<User> users = dao.findAll();
        for( User one : users ){
            System.out.println( one );
        }

        //7.释放资源：后打开的先关闭
        session.close();
        is.close();

    }
}
