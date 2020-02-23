package pers.yo.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JedisPoolUtils {

    /* 后面的 静态方法getJedis()需要从 Jedis连接池中取出某个连接对象jedis
    * 所以这个成员变量 jedisPool 要声明为 静态变量static
    *  */
    private static JedisPool jedisPool;

    /* 2020-02-23 12:38:20
    * 把电视剧列表url、剧集详情url全塞进同一个 redis url仓库中，
    * 并用 高优先级、低优先级区分 列表url和详情url
    * 高优先级-列表url
    * 低优先级-详情url
    * */
    public static String highKey = "spider.highLevel";
    public static String lowKey = "spider.lowLevel";

    static{ //静态代码块，加载配置文件
        try{
            Properties prop = new Properties(); //读取配置文件的数据
            /* 反射中 获取 Class类_类型实例对象
            * (1)Class.forName( "全路径名，包名.类名" )
            * (2)类名.class属性
            * (3)任意实例对象.getClass()方法
            *  */
            InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream( "jedis.properties" );
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

    public static List<String> lrange( String key, int start, int end ){
        Jedis jedis = JedisPoolUtils.getJedis();
        List<String> list = jedis.lrange( key,start,end );
        jedis.close(); //关闭连接
        return list;
    }

    public static void add( String key, String url ){ //添加
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.lpush( key,url ); //加入 left push
        jedis.close();
    }

    public static String poll( String key ){ //获取
        Jedis jedis = JedisPoolUtils.getJedis();
        String rst = jedis.rpop(key); //获取 right pop
        jedis.close();
        return rst;
    }


}