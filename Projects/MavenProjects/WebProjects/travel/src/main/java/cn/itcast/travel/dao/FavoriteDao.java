package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {

    //根据rid和uid查询收藏信息
    public abstract Favorite findByRidAndUid( int rid, int uid );

    //根据rid查询收藏次数
    public abstract int findCountByRid( int rid );

    //添加收藏
    public abstract void add( int rid, int uid );
}
