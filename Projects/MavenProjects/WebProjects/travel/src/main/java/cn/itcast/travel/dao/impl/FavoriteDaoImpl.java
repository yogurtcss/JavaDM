package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ? ";
        Favorite fav_final = null; //涉及到从数据库取值时，用try...catch，或直接return之
        try{
            fav_final = template.queryForObject( sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid );
        }catch( Exception e ){
            //不处理
        }

        return fav_final;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        //涉及到从数据库取值时，用try...catch，或直接return之
        return( template.queryForObject(sql, Integer.class, rid) );
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values( ?,?,? )";
        //增、删、改 都是用update方法嗷！
        template.update(
                sql,
                rid,  new Date(),  uid
        );
    }
}
