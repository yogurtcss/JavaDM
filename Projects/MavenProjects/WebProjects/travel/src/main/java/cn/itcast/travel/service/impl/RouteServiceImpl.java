package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        RouteDao dao = new RouteDaoImpl(); //数据库dao层

        /* 封装PageBean实例对象：其中的内容有：
        int totalCount; //当前cid类别下的 总记录数
        int totalPage; //当前cid类别下的 总页数
        int currentPage; //当前cid类别下的 当前页的当前页码
        int pageSize; //当前cid类别下的 当前页的显示的条数
        List<T> list; //当前cid类别下的 分页对象，显示真正的内容
        *  */
        PageBean<Route> pb_final = new PageBean<Route>();

        //-----获取当前类别下的总记录数
        int totalCount = 0;
        try{
            totalCount = dao.findTotalCount( cid, rname );
        }catch( Exception e ){
            //不处理
        }
        pb_final.setTotalCount( totalCount ); //设置当前类别cid的 总记录数

        pb_final.setCurrentPage( currentPage ); //设置当前cid类别下的 当前页的当前页码
        pb_final.setPageSize( pageSize ); //当前cid类别下的 当前页的显示的条数

        /* -----总页码的计算公式：三目运算符
        * (所有页码的总记录数totalCount)%(每页显示条数rows)==0 ? (totalCount/整除rows) : (totalCount/除以rows +1)
        *
        * (所有页码的总记录数totalCount)%(每页显示条数rows)==0 直接相除，余数等于0吗？
        * 是(即余数等于0)，就用此式计算(totalCount/整除rows)
        * 否则(余数不等于0，除不尽)，就用此式计算(totalCount/除以rows +1)
        *  */
        int totalPage = (totalCount%pageSize==0) ? (totalCount/pageSize):((totalCount/pageSize)+1);
        pb_final.setTotalPage( totalPage ); //设置 当前cid类别下的 总页数


        /* -----获取 list内容咯！
        * dao.findByPage( cid, start开始索引, pageSize )
        *  */
        int start = (currentPage-1)*pageSize; //每页的开始索引为 (当前页码-1)×每页显示条数
        List<Route> list = null;
        try{
            list = dao.findByPage( cid, start, pageSize, rname );
        }catch( Exception e ){
            //不处理
        }
        pb_final.setList( list );

        return pb_final;
    }

    @Override
    public Route findOne(String rid) {
        RouteDao Rdao = new RouteDaoImpl(); //接口回调，向上转型
        RouteImgDao RIdao = new RouteImgDaoImpl();
        SellerDao Sdao = new SellerDaoImpl();

        /* Route实例对象中的成员变量：
        private Seller seller;//所属商家
        private List<RouteImg> routeImgList;//商品详情图片列表
        *  */
        //---凡是涉及从数据库中取查询结果的，都要 try...catch...
        Route route_final = null; //待填充的route实例对象
        List<RouteImg> temp_list_RouteImg = null; //临时变量list_RouteImg，用来 暂存数据库中查询出来的值
        Seller temp_seller = null; //临时变量temp_seller，用来 暂存数据库中查询出来的值

        try{ //传入整型的rid！
            route_final = Rdao.findOne( Integer.parseInt(rid) ); //查询得出Route实例对象
        }catch( Exception e ){
            //不处理
        }
        try{
            //根据 查询出来的Route实例对象的 rid，获取详情图片
            temp_list_RouteImg = RIdao.findByRid( route_final.getRid() );
            //根据 查询出来的Route实例对象的 sid，获取商家
            temp_seller = Sdao.findById( route_final.getSid() );
        }catch( Exception e ){
            //不处理
        }

        //往route实例对象中填充数据喽！
        route_final.setRouteImgList( temp_list_RouteImg );
        route_final.setSeller( temp_seller );

        return route_final;
    }
}
