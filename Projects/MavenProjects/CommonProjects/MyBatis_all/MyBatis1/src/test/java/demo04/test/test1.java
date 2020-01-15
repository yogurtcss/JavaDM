package demo04.test;


/* 2020-01-15 09:59:56  测试类
* init()：读取SqlMapConfig.xml、创建SqlSession实例对象、和持久层接口UserDao的代理对象dao_proxy
* destroy()：提交事务(成批SQL语句，确保能操作到数据库)，释放资源
*  */

import demo04_DynamicSQL.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

public class test1 {
    private InputStream is; //读取SqlMapConfig.xml总配置文件的流
    private SqlSession session;
    private UserDao dao_proxy; //持久层接口UserDao的代理对象

    @Before
    public void init() throws IOException {
        //使用Resources工具类中的静态方法，读取SqlMapConfig.xml总配置文件
        is = Resources.getResourceAsStream( "SqlMapConfig.xml" );
        /* 首先 创建出 构建者实例对象builder： new SqlSessionFactoryBuilder(); --builder
        * 由 【构建者实例对象builder，传入配置文件的字节输入流is】 创造出 SqlSessionFactory工厂 ： builder.build(is) --factory
        * 再由 SqlSessionFactory工厂 生产出 session数据库会话对象： factory.openSession()
        * 那我一步到位吧
        *  */
        session = new SqlSessionFactoryBuilder().build(is).openSession();
    }



}
