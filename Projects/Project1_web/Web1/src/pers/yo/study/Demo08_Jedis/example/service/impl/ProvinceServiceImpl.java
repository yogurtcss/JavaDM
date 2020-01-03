package pers.yo.study.Demo08_Jedis.example.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.yo.study.Demo08_Jedis.example.dao.ProvinceDao;
import pers.yo.study.Demo08_Jedis.example.dao.impl.ProvinceDaoImpl;
import pers.yo.study.Demo08_Jedis.example.domain.Province;
import pers.yo.study.Demo08_Jedis.example.service.ProvinceService;
import pers.yo.study.Demo08_Jedis.example.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    //声明dao层的实例对象

    /*
    * 接口回调：可以把某一接口实现类的实例对象 赋给 该接口声明的接口变量，
    * 那么该接口变量就可以调用被【此实现类】所实现的 【接口】中的方法。
    *  */
    private ProvinceDao dao = new ProvinceDaoImpl(); //向上转型。在接口中的“向上转型” 特指 “接口回调”
    /* ——也可以说是 接口实现类实例对象 向上转型 为接口类型
    *
    * 【接口回调与向上转型 实质上效果是一样的】
    * 这些 向上转型后的对象 都可以调用【基类型】提供的方法
    *  */
    // private ProvinceDaoImpl daoImpl = new ProvinceDaoImpl();

    /* 接口回调的概念，强调使用接口来实现回调对象方法使用权的功能 。
    * 而向上转型则牵涉到多态和运行期绑定的范畴。
    *  */
    @Override //IDEA快速生成 需要重写的方法
    public List<Province> findAll() {
        return( dao.findAll() );
    }

    @Override
    public String findAllJson() { //redis优化缓存的方法

        //-----------------1.先从redis数据库中查询数据
        //-----1.1 获取一个jedis连接对象
        Jedis jedis = JedisPoolUtils.getJedis();
        //尝试性地：在redis数据库中查看有无 键名为province 对应的值？
        String province_json = jedis.get( "province" ); //若此键没有值，我仍以键名为"province"，存新的值进去嗷

        //-----------------2.判断redis数据库中的 province_json是否为空
        if( province_json==null || province_json.length()==0 ){
            /* 若此键的值是空的，说明redis数据库中没有缓存
            * 那我就从真正的 mysql数据库中，查得数据data
            * 并将此数据data 对应着键名"province" 存入 redis数据库中，
            * 这样 数据data就有缓存了！
            *  */
            System.out.println( "redis数据库中没有此数据，正从MySQL中查询数据..." );
            //------2.1这里是service层，往下调用最底层的dao！！
            List<Province> rst = dao.findAll();

            //------2.2将用MySQL查询的数据转为JSON格式，然后存入 redis数据库中
            ObjectMapper om = new ObjectMapper(); //Jackson核心对象
            try { //使用 IDEA 快速帮我捕获异常！
                province_json = om.writeValueAsString( rst );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //------2.3将此JSON数据 以键名province存入redis数据库中
            jedis.set( "province", province_json );
            //------2.4释放资源，把连接对象给回连接池
            jedis.close();
        }else{
            System.out.println( "redis中有缓存数据，查询缓存嗷！" );
        }

        return province_json;
    }
}
