package demo03.test;

import demo03_CRUD.dao.UserDao;
import demo03_CRUD.domain.QueryVo;
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
    private InputStream is; //从类路径加载 SQL Map 配置文件（如 sqlMap-config.xml）
    private SqlSession session; //数据库会话对象
    private UserDao dao_proxy;

    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //使用Resources工具类。从类路径加载 SQL Map 配置文件（如 sqlMap-config.xml）
        is = Resources.getResourceAsStream( "SqlMapConfig.xml" ); //读取总配置文件SqlMapConfig.xml
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is); //根据 构建者对象，创建SqlSessionFactory工厂对象
        session = factory.openSession(); //根据工厂，生成一个 数据库会话对象
        dao_proxy = session.getMapper( UserDao.class ); //在数据库会话对象中，由 接口的字节码Class对象，生成UserDao的代理对象

        /*
        SqlSessionFactoryBuilder 用于创建 SqlSessionFactory，
        SqlSessionFactory 一旦创建完成就不需要 SqlSessionFactoryBuilder 了，
        因为 SqlSession 是通过 SqlSessionFactory 生产，
        所以可以将 SqlSessionFactoryBuilder 当成一个工具类使用，
        最佳使用范围是方法范围即方法体内局部变量。



        *  */
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

    @Test
    public void testUpdate(){
        User user = new User();
        //设置 将要被更新数据 的用户ID
        user.setUserId(49); //被更新数据的用户ID为 49
        user.setUserName("我佛了我佛了");
        user.setUserAddress("佛佛佛佛佛佛佛佛");
        user.setUserSex("哈");
        user.setUserBirthday(new Date());

        dao_proxy.updateUser( user );
        System.out.println( "ID为："+user.getUserId()+" 的用户，其数据更新完成！" );
    }

    @Test
    public void testDelete(){
        dao_proxy.deleteUser( 55 );
        System.out.println( "删除完成！" );
    }

    @Test
    public void testFindById(){
        System.out.println( dao_proxy.findById(46) );
    }

    @Test
    public void testFindByName(){ //SQL语句中 like 模糊查询，传入的字符串必需 带百分号！
        System.out.println( dao_proxy.findByName("%王%") );
    }

    @Test
    public void testFindTotal(){
        System.out.println( "数据库中user表的总行数为："+dao_proxy.findTotal() );
    }

    @Test
    public void testFindUserByVo(){
        QueryVo vo = new QueryVo();
        //随便定义的一个user实例对象
        User user = new User();
        user.setUserName( "A01" );
        user.setUserAddress( "北京" );
        user.setUserSex( "女" );
        //为vo对象设置属性 user
        vo.setUser( user );

        /* 在 映射配置文件UserDao.xml中，我是这样写SQL语句：
        根据用户的地址，查询这个用户
        select * from user where address=#{user.userAddress};
        *  */
        System.out.println( "testFindUserByVo()的结果："+dao_proxy.findUserByVo(vo) );
    }
}
