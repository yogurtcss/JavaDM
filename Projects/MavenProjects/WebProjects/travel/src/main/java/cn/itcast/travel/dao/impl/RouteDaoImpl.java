package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override
    public int findTotalCount(int cid, String rname) { //查询某页下，共有多少项数据
        //String sql = "select count(*) from tab_route where cid=? and rname=?";
        /* cid和rname可能不会同时出现！需要动态拼接sql语句！
        *
        *  */
        String sql = "select count(*) from tab_route where 1=1   "; //注意，这句话是拼接处，末尾要加空格！
        StringBuilder sb = new StringBuilder( sql );

        List<Object> params = new ArrayList<>(); //条件们，注意List的泛型是Object！
        if( cid!=0 ){
            sb.append( " and cid=? " ); //注意拼接处的空格！
            params.add( cid ); //添加 ?问号对应的值
        }
        /* 2020-01-11 16:45:34 排了整整大半天的BUG
        * 【问题描述】当不传入rname时(即rname为null时)。
        * 在前端页面 route_list.html 中，不能进行翻页！(第2、3、以后的页为空）
        * 【解决】在关于rname的判空操作中，加上一个 “且”的条件！！ ：
        * !rname.equals("null") 要求 rname字符串的内容 不为 "null" ！！
        *
        * 若不检查 rname字符串的内容 是否为 "null"，
        * 则动态添加的sql语句中，就会把 and rname like ? 给加上；
        * 而 rname为"null" --在数据库中找不到值！
        * 所以 在前端页面 route_list.html 中，就不能进行翻页！(第2、3、以后的页为空）
        *
        * 前端代码中，只需检查一下 关于rname变量的 <li />标签有无写错即可！！
        * <li />标签的拼串处，很恶心！
        *  */
        if( rname!=null && rname.length()>0 && !rname.equals("null")){ //模糊查询 like关键字
            sb.append( " and rname like ? " ); //注意拼接处的空格！
            params.add( "%"+rname+"%" ); //添加 ?问号对应的值，模糊查询，要加百分号！
        }
        sql = sb.toString(); //将StringBuilder还原为字符串嗷！
        System.out.println( "rname是空的吗？"+rname );
        System.out.println( "sql语句为："+sql );
        return template.queryForObject( sql, Integer.class, params.toArray() );
    }

    @Override //int pageSize; //每页显示的条数
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid=? limit ?,?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder( sql );

        List<Object> params = new ArrayList<>(); //条件们
        if( cid!=0 ){
            sb.append( " and cid = ? " );
            params.add( cid ); //添加 ? 对应的值
        }
        /* 2020-01-11 16:45:34 排了整整大半天的BUG
        * 【问题描述】当不传入rname时(即rname为null时)。
        * 在前端页面 route_list.html 中，不能进行翻页！(第2、3、以后的页为空）
        * 【解决】在关于rname的判空操作中，加上一个 “且”的条件！！ ：
        * !rname.equals("null") 要求 rname字符串的内容 不为 "null" ！！
        *
        * 若不检查 rname字符串的内容 是否为 "null"，
        * 则动态添加的sql语句中，就会把 and rname like ? 给加上；
        * 而 rname为"null" --在数据库中找不到值！
        * 所以 在前端页面 route_list.html 中，就不能进行翻页！(第2、3、以后的页为空）
        *
        * 前端代码中，只需检查一下 关于rname变量的 <li />标签有无写错即可！！
        * <li />标签的拼串处，很恶心！
        *  */
        if( rname!=null && rname.length()>0 && !rname.equals("null") ){
            sb.append( " and rname like ? " ); //模糊查询
            params.add( "%"+rname+"%" ); //模糊查询 加百分号！
        }
        sb.append( " limit ?, ? " ); //分页条件
        sql = sb.toString(); //转为字符串

        params.add( start );
        params.add( pageSize );
        System.out.println( "sql语句为："+sql );
        return( template.query(
                sql,
                new BeanPropertyRowMapper<Route>(Route.class),
                // cid, start, pageSize //不传入这些参数了
                params.toArray()
        ) );
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid=? ";
        return( template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid) );
    }
}
