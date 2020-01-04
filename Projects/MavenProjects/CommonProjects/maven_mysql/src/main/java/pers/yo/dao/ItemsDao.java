package pers.yo.dao;

import pers.yo.domain.Items;

import java.sql.SQLException;
import java.util.List;

public interface ItemsDao {
    /* 接口中的方法默认的就是 public abstract；可以不写
    * 为了表示规范，一般还是写上
    *  */
    public abstract List<Items> findAll() throws SQLException;
}
