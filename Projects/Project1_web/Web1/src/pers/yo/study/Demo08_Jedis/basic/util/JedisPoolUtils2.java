package pers.yo.study.Demo08_Jedis.basic.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//2020-01-03 12:52:09
public class JedisPoolUtils2 { // JedisPoolUtils2工具类
    private static JedisPool jedisPool; //私有 静态 变量(类似 本类中的全局变量！)：连接池对象jedisPool
    /* 反射中，如何获取 Class类_类型实例对象？
    * (1)Class.forName("全类名：即包名.类名")
    * (2)类名.class属性
    * (3)任意实例对象.getClass()
    *  */
    static{ //静态代码块：加载配置文件
        /* 注意，原本的相对路径末尾已经有 斜杠/了！此相对路径不要以斜杠/开头！！
        * 如果不能连接，那就手动把配置文件复制到 out/production/项目名/pers/yo/props/ 这里来！！
        *  */
        InputStream is = JedisPoolUtils2.class.getClassLoader().getResourceAsStream("pers/yo/props/jedis.properties");
        Properties prop = new Properties(); //准备一个properties实例对象 填入配置文件的数据
        //prop.load(is) 我手动捕获异常！
        try{
            prop.load(is);
        }catch( IOException e ){
            e.printStackTrace();
        }
        //读取完成后，自定义JedisPoolConfig 配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //注意：properties实例对象中的 键、值 全是字符串类型的！！需要手动强制类型转换！
        config.setMaxTotal( Integer.parseInt(prop.getProperty("maxTotal")) ); //Jedis连接池中的最大数量
        config.setMaxIdle( Integer.parseInt(prop.getProperty("maxIdle")) ); //Jedis连接池中 Jedis连接对象的最大空闲数量

        //利用自定义的config对象，初始化JedisPool连接池对象
        jedisPool = new JedisPool(
                config, //自定义的config实例对象
                prop.getProperty( "host" ), //主机名
                Integer.parseInt( prop.getProperty("port") ) //端口号，需要强制类型转换
        );
    }

    public static Jedis getJedis(){
        return( jedisPool.getResource() );
    }
}
