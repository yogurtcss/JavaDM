package pers.yo.study.Demo08_Jedis.example.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    /* 后面的 静态方法getJedis()需要从 Jedis连接池中取出某个连接对象jedis
    * 所以这个成员变量 jedisPool 要声明为 静态变量static
    *  */
    private static JedisPool jedisPool;

    static{ //静态代码块，加载配置文件
        try{
            Properties prop = new Properties(); //读取配置文件的数据
            /* 反射中 获取 Class类_类型实例对象
            * (1)Class.forName( "全路径名，包名.类名" )
            * (2)类名.class属性
            * (3)任意实例对象.getClass()方法
            *  */
            InputStream is = JedisPoolUtils.class.getResourceAsStream( "pers/yo/props/jedis.properties" );
            prop.load( is ); //正式读取数据

            //准备JedisPoolConfig配置对象
            JedisPoolConfig config = new JedisPoolConfig();
            //注意，properties集合中的 键和值 都是String型的！！
            config.setMaxTotal( Integer.parseInt(prop.getProperty("maxTotal")) ); //设置连接池中可容纳连接对象的最大数量
            config.setMaxIdle( Integer.parseInt(prop.getProperty("maxIdle")) );//连接池中 空闲连接对象 的最大数量
            //根据配置对象config  初始化 jedis连接池对象
            jedisPool = new JedisPool(
                    config,
                    prop.getProperty("host"), //主机名
                    Integer.parseInt( prop.getProperty("port") ) //端口号
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    /* 工具类的静态方法：返回Jedis连接池中的一个 jedis连接对象
    * 使用 jedisPool.getResource() ; //可能会忘记！
    * 因为此静态方法中使用了 变量jedisPool，
    * 所以在开头 变量jedisPool需声明为 静态的 static
    *  */
    public static Jedis getJedis(){
        return( jedisPool.getResource() );
    };
}