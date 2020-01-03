package pers.yo.study.Demo08_Jedis.example.service;

import pers.yo.study.Demo08_Jedis.example.domain.Province;

import java.util.List;

public interface ProvinceService {
    public abstract List<Province> findAll();

    public abstract String findAllJson();  //redis缓存优化的方法
}
