package demo04.test;


/* 2020-01-15 09:59:56  测试类
* init()：读取SqlMapConfig.xml、创建SqlSession实例对象、和持久层接口UserDao的代理对象dao_proxy
* destroy()：提交事务(提交成批的SQL语句，确保能操作到数据库)，释放资源
*  */

import demo04_DynamicSQL.dao.UserDao;
import demo04_DynamicSQL.domain.QueryVo;
import demo04_DynamicSQL.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        //根据 持久层接口的Class字节码对象，反射，创建出 代理对象dao_proxy
        dao_proxy = session.getMapper( UserDao.class );
    }

    @After
    public void destroy() throws IOException {
        session.commit(); //前面 @Test注解方法执行后，手动提交事务嗷！
        session.close(); //释放资源，后打开的先关闭
        is.close();
    }

    @Test
    public void testFindAll(){
        System.out.println( dao_proxy.findAll() );
    }

    @Test
    public void test_IF_test(){
        // List<E> SqlFactorySession实例对象.selectList( String SQL语句， Object parameter传入的形参  )

        /* if标签中，test属性的 OGNL表达式 所获取的值是 你传入形参parameterType 中的属性！
        *
        * 如 传入形参parameterType 为 User实例对象user
        * 则 if标签中，test属性的 OGNL表达式 所获取的值 是User实例对象的 属性名！
        * 如 userName、userAddress
        *  */

    }

    @Test
    public void testFindById(){
        System.out.println( dao_proxy.findById(45) );
    }

    @Test
    public void testFindByName(){ //模糊查询，传形参时要把双引号带上！
        System.out.println( dao_proxy.findByName("%王%") );
    }

    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserId(45); //实例对象user：我只设置了这个id而已
        vo.setUser( user );

        System.out.println( dao_proxy.findUserByVo(vo) );
    }

    /* 插入测试数据嗷
        测试 <if />标签 拼接的SQL语句是否生效  */
    /* insert into user(username) values("a_name");
       insert into user(username,sex) values("a_nameSex","啊");
       insert into user(username,sex,address) values("a_nameSexAddr","嗯","中国嗷"); */
    @Test
    public void testFindUserByCondition(){
        User u1 = new User();
        u1.setUserName( "a_name" );

        User u2 = new User();
        //u2.setUserName( "a_nameSex" );
        u2.setUserSex( "啊" );

        User u3 = new User();
        //u3.setUserName( "a_nameSex" );
        //u3.setUserSex( "嗯" );
        u3.setUserAddress( "中国嗷" );

        System.out.println( "只有username属性："+dao_proxy.findUserByCondition(u1) );
        System.out.println( "有username属性 和 userSex属性："+dao_proxy.findUserByCondition(u2) );
        System.out.println( "有username属性 和 userSex属性 和 userAddress属性："+dao_proxy.findUserByCondition(u3) );
    }

    @Test
    public void testFindUserInIds(){
        QueryVo vo = new QueryVo();
        //vo实例对象中的属性：List<Integer> ids;
        List<Integer> ids = new ArrayList<>(); //接口回调，向上转型
        ids.add(41);
        ids.add(45);
        ids.add(49);
        vo.setIds( ids );

        System.out.println( "根据QueryVo实例对象中的ids集合，查询："+dao_proxy.findUserInIds(vo) );
    }

}
