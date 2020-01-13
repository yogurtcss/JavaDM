package demo01.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import demo01.dao.UserDao;
import demo01.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* 2020-01-12 20:32:36
* 此 MybatisTest1.java 解释了自定义MyBatis框架的细节，如注释所说，共有7步；
* 了解mybatis 内部是怎么执行的，在以后的开发中能更好的使用 mybatis 框架，
* 同时对它的设计理念（设计模式）有一个认识。
*  */

public class test1 {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        /* 读取时，可使用
        (1)类加载器：只能读取类路径classpath下的配置文件
        (2)ServletContext对象的 getRealPath()
        *  */
        InputStream is = Resources.getResourceAsStream("props/SqlMapConfig_demo1.xml");

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
        //创建 SqlSession 只有一个途径(方法)，那就是 调用SqlSessionFactory实例对象的openSession()方法：--打开一个sql会话
        SqlSession session = factory.openSession(); //SqlSession是接口类型
        /* SqlSession接口的介绍：
        为Mybatis工作最重要的java接口，通过这个接口来执行命令(sql语句)，获取(增强后的)代理对象mapper 以及管理事务

        ▲ SqlSession，顾名思义，就是 sql的一个会话。在这个会话中发生的事不影响别的会话，如果此会话提交，则生效；不提交不生效。
        SqlSession 是 Mybatis 工作的最顶层 API 会话接口，所有的数据库操作都经由它来实现，
        由于它就是一个会话，即一个 SqlSession 应该仅存活于一个业务请求中，
        也可以说一个 SqlSession 对应这一次数据库会话，它不是永久存活的，每次访问数据库时都需要创建它。


        ▲ SqlSession的创建过程

        1.从 Configuration 配置类中拿到 Environment 数据源；
        2.从数据源中获取 TransactionFactory 和 DataSource，并创建一个 Transaction 连接管理对象；
        3.创建 Executor 对象（SqlSession 只是所有操作的门面，真正要干活的是 Executor，它封装了底层 JDBC 所有的操作细节）；
        4.创建 SqlSession 会话。

        *  */



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
