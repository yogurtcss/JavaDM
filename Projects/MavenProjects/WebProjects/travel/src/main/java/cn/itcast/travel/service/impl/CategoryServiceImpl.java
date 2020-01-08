package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryDao {
    private CategoryDao dao = new CategoryServiceImpl(); //接口回调，向上转型

    @Override //使用IDEA快速生成需重写的方法
    public List<Category> findAll() {
        return( dao.findAll());
    }
}
