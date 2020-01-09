package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl(); //接口回调，向上转型

    @Override //使用IDEA快速生成需重写的方法
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis(); //获取到某个jedis连接对象

        //---1.从redis中查询：使用sortedset 排序查询
        Set<String> category_redis = jedis.zrange( "category", 0, -1 );

        List<Category> list = null; //最终要返回的结果 集合list，注意，这是List接口类型的数据！
        //---2.判断需查询的集合是否为空
        if( category_redis==null || category_redis.size()==0 ){ //------2.1 如果为空，则从本地数据库mysql中查询，然后将数据存入redis中
            System.out.println( "从数据库中查询..." );
            try{
                /* 若查询的缓存中没有数据category：这是第一次查询，就直接从数据库中查询得到List接口类型的数据list，并返回这list数据
                * 并偷偷地把list中的数据加入缓存中，留待下一次 查询缓存
                *  */
                list = dao.findAll();
            }catch( Exception e ){
                //不处理
            }
            // 并偷偷地把list中的数据加入缓存中，留待下一次 查询缓存
            // list的数据格式：[ {cid:xxx,cname:yyy}, {cid:aaa,cname:bbb}, .... ]
            for( Category one: list ){ //遍历每一个对象，把id、name加入至redis缓存中
                //System.out.println( one );
                jedis.zadd( "category", one.getCid(), one.getCname() );
            }
        }else{ //如果查询出来的结果category_redis不为空
            /* 因为从redis查询出来的结果是Set类型，
            * 而此方法的返回值是List<Category泛型>类型，
            * 在这里转为ListList<Category泛型>类型，最终再返回
            *  */
            System.out.println( "从redis中查询..." );
            //list原本是List接口类型的，需向下转型为ArrayList类型，才能用list.add()方法
            list = new ArrayList<Category>();
            for( String one : category_redis ){
                //新建一个实例对象Category c，作为list列表中的泛型！！
                Category c = new Category();
                c.setCname( one ); //把名字放进c对象中！
                list.add(c); //添加这个实例对象Category c
            }
        }
        /* 最终返回这个结果list
        * 若查询的缓存中没有数据category：这是第一次查询，就直接从数据库中查询得到List接口类型的数据list，并返回这list数据
        * 并偷偷地把list中的数据加入缓存中，留待下一次 查询缓存
        *
        * 若查询的缓存中有数据category：而我缓存查出来的数据是Set类型的，转为List接口类型的数据再返回；
        *
        * 所以，无论如何，最终都是会返回 List接口类型<Category泛型>的数据！！
        *  */
        return list;
    }
}
