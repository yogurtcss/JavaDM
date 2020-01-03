package pers.yo.study.Demo08_Jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JedisTest1 {
    @Test
    public void test1(){
        //1.获取连接
        Jedis jedis = new Jedis( "localhost", 6379 );
        //2.操作
        jedis.set( "username", "zhangsan" );
        //3.关闭连接
        jedis.close();
    }

    @Test
    public void test2(){ //String
        //---------1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值为 "localhost", 6379端口
        //---------2.操作
        //存储
        jedis.set( "username","hahaha" );
        String username = jedis.get( "username" );
        System.out.println( username );
    }

    @Test
    public void test3(){ //hash
        //---1.获取连接
        Jedis jedis = new Jedis( "localhost",6379 );
        //---2.操作
        //存储map集合
        jedis.hset( "user","name","lisi" );
        jedis.hset( "user","age","23" );
        jedis.hset( "user","gender","female" );
        //获取hash
        String name = jedis.hget( "user","name" );
        System.out.println( name );
        System.out.println( "----------" );

        //获取hash中 某个key对应的集合map
        Map<String,String> user = jedis.hgetAll( "user" );
        //直接用迭代器 遍历map的键值对！
        /* 返回值类型是 集合Set；
        * 而Set中的泛型是 键值对型【Map.Entry< 键的泛型，值的泛型 >】
        *  */
        Set< Map.Entry<String,String> > entrys = user.entrySet();
        //迭代器的泛型是 键值对型【Map.Entry< 键的泛型，值的泛型 >】
        Iterator< Map.Entry<String,String> > it = entrys.iterator();
        //遍历迭代器
        while( it.hasNext() ){
            Map.Entry<String,String> oneEntry = it.next();
            System.out.println( oneEntry );
        }

        //---3.结束连接
        jedis.close();
    }

    @Test
    public void test4(){ //list，类比python中的“列表”，java中的ArrayList
        //---1.获取连接
        Jedis jedis = new Jedis( "localhost",6379 );
        //---2.操作
        jedis.lpush( "mylist", "a","b","c" ); //总是从左边开始存数据。所以数据顺序为：c b a
        jedis.rpush( "mylist", "a","b","c" ); //总是从右边开始存数据。此时的数据顺序为：a b c
        //此时mylist中的数据，从左到右顺序为： c b a a b c

        //取出mylist中的所有数据
        List<String> mylist1 = jedis.lrange( "mylist", 0, -1 );
        System.out.println( mylist1 ); //此时mylist中的数据，从左到右顺序为： c b a a b c
        //输出的是数组 [ c,b,a,a,b,c ]

        String e1 = jedis.lpop( "mylist" ); //总是弹出左边第一个元素。这里弹出的是左边第一个c
        System.out.println( e1 );
        String e2 = jedis.rpop( "mylist" ); //总是弹出右边第一个元素。这里弹出的是右边第一个c
        System.out.println( e2 );

        //再次查询一下此时的list
        System.out.println( jedis.lrange( "mylist",0,-1 ) );
        //---3.关闭连接
        jedis.close();
    }

    @Test
    public void test5(){ //set 数据结构操作
        //---1.获取连接
        Jedis jedis = new Jedis( "localhost",6379 );
        //---2.操作

        //set存储
        jedis.sadd( "myset", "java", "php", "C++" );
        //set获取
        Set<String> myset = jedis.smembers( "myset" );
        System.out.println( myset );
        //---3.关闭连接
        jedis.close();
    }

    @Test
    public void test6(){ //sortedset 数据结构操作
        //---1.获取连接
        Jedis jedis = new Jedis( "localhost", 6379 );
        //---2.操作
        //sortedset存储
        jedis.zadd( "mysortedset", 3, "haha" );
        jedis.zadd( "mysortedset", 20, "bbb" );
        jedis.zadd( "mysortedset",100, "您好" );
        //sortedset获取
        Set<String> mysortedset = jedis.zrange( "mysortedset",0,-1 );
        System.out.println( mysortedset );
        //---3.关闭连接
        jedis.close();
    }

}
