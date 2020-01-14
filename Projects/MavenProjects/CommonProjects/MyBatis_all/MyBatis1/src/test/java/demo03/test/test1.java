package demo03.test;

import demo03_CRUD.dao.UserDao;
import demo03_CRUD.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class test1 {
    private InputStream is;
    private SqlSession session; //数据库会话对象
    private UserDao dao_proxy;

    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        is = Resources.getResourceAsStream( "SqlMapConfig.xml" ); //读取总配置文件SqlMapConfig.xml
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is); //根据 构建者对象，创建SqlSessionFactory工厂对象
        session = factory.openSession(); //根据工厂，生成一个 数据库会话对象
        dao_proxy = session.getMapper( UserDao.class ); //在数据库会话对象中，由 接口的字节码Class对象，生成UserDao的代理对象
    }

    @After //用于在测试方法执行之后执行
    public void destory() throws IOException {
        /* 前面 @Test注解的方法，是对数据库的一顿操作
        * 如 增、删、改、查……
        *
        * 这里是 @After注解的方法，在测试方法执行之后执行
        * 说明前面已经执行过对数据库的操作了，
        * 这时需要【手动提交事务 commit】，
        * 否则：测试会成功，但数据库的数据不会改变！
        *  */
        session.commit(); //手动提交事务
        session.close(); //释放资源，后打开的先关闭
        is.close();
    }

    @Test
    public void testFindAll(){
        //使用代理对象dao_proxy来执行接口中的方法
        List<User> users = dao_proxy.findAll();
        for( User one : users ){
            System.out.println( one );
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName( "A01" );
        user.setUserAddress( "北京市海淀区" );
        user.setUserSex( "女" );
        user.setUserBirthday( new Date() );
        System.out.println( "保存操作之前："+user+"，没有为ID赋值嗷！" );
        /* 在 <insert />标签中，加入了子标签 <selectKey />
        *  */
        dao_proxy.saveUser(user);
        System.out.println( "保存操作之后："+user+"，多了一个自增ID："+user.getUserId() );
    }




}
