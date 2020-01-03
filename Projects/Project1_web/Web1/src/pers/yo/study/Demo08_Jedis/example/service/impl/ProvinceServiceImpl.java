package pers.yo.study.Demo08_Jedis.example.service.impl;

import pers.yo.study.Demo08_Jedis.example.dao.ProvinceDao;
import pers.yo.study.Demo08_Jedis.example.dao.impl.ProvinceDaoImpl;
import pers.yo.study.Demo08_Jedis.example.domain.Province;
import pers.yo.study.Demo08_Jedis.example.service.ProvinceService;

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
}
