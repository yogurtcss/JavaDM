package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    //根据类别，进行分页查询
    public abstract PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname );

    public abstract Route findOne( String rid );
}
