package cn.itcast.travel.service;

public interface FavoriteService {
    //查询 该uid的用户是否收藏了 rid的商品
    public abstract boolean isFavorite( String rid, int uid );
    //某uid的用户 添加收藏rid的商品
    public abstract void add( String rid ,int uid );
}
