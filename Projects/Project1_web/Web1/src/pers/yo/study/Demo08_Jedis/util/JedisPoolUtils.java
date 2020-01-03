package pers.yo.study.Demo08_Jedis.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    //Jedis连接池对象
    private static JedisPool jedisPool;

    static{ //静态代码块，读取配置文件，注意配置文件路径的写法！
        //如果找不到，那就手动把配置文件 复制到 out输出文件夹/production/Web1项目名/pers/yo/props/ 下！
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream( "pers/yo/props/jedis.properties" );
        System.out.println( JedisPoolUtils.class.getClassLoader().getResource("") );
        //   file:/H:/ProcExes/JavaFiles/JavaDM/Projects/Project1_web/out/production/Web1/
        //此输出路径已经自带斜杠了！所以，在上面的路径 pers/yo/props/jedis.properties中，开头不要有斜杠!!
        if( is==null ){
            System.out.println( "空的！" );
        }
        Properties prop = new Properties(); //创建properties实例对象，准备放入配置文件的数据
        try { //IDEA 快速帮我生成 捕获异常！
            prop.load( is ); //读取配置文件的数据
        } catch (IOException e) {
            e.printStackTrace();
        }
        //自定义 配置对象JedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        /* 注意，properties实例对象中的数据：每个键及其对应值 都是【字符串类型】，
        * 需要强制类型转换
        *
        * String maxTotal = prop.getProperty("maxTotal"); //连接池最大数量
        * String maxIdle = prop.getProperty("maxIdle"); //连接池最大空闲对象
        * String port = prop.getProperty("port"); //端口号，这里刚取出来的是字符串型！
        *  */
        config.setMaxTotal( Integer.parseInt(prop.getProperty("maxTotal")) ); //连接池最大数量
        config.setMaxIdle( Integer.parseInt(prop.getProperty("maxIdle")) ); //连接池最大空闲对象

        //根据已经自定义好的config配置对象，进行初始化JedisPool 连接池对象
        jedisPool = new JedisPool(
                config,
                prop.getProperty( "host" ), //连接的主机名
                Integer.parseInt( prop.getProperty("port") ) //强制转换为整型的端口号
        );
    }

    //获取连接对象的静态方法
    public static Jedis getJedis(){
        return( jedisPool.getResource() ); //返回某个jedis连接对象
    }
}
