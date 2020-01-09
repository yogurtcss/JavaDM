package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override
    public int findTotalCount(int cid) { //查询某页下，共有多少项数据
        String sql = "select count(*) from tab_route where cid=?";
        int rst = 0;
        try{ //数据库的赋值，全在try中进行嗷！
            rst = template.queryForObject( sql, Integer.class, cid );
        }catch( Exception e ){
            //不处理
        }

        return rst;
    }

    @Override //int pageSize; //每页显示的条数
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid=? limit ?,?";
        return( template.query(
                sql,
                new BeanPropertyRowMapper<Route>(Route.class),
                cid, start, pageSize
        ) );
    }
}
