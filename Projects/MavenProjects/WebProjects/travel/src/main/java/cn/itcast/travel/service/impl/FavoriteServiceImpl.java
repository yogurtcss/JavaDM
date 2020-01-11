package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl(); //接口回调，向上转型

    @Override
    public boolean isFavorite(String rid, int uid) {
        // dao.findByRidAndUid(Integer.parseInt(rid),uid)==null 查出来为空吗？ 是就返回false，否则返回true喽
        return( dao.findByRidAndUid(Integer.parseInt(rid),uid)==null ? false : true );
    }

    @Override
    public void add(String rid, int uid) {
        dao.add( Integer.parseInt(rid), uid );
    }
}
