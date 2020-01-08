package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    //根据数据库连接池，生成 JdbcTemplate实例对象，来操作数据库
    private JdbcTemplate template = new JdbcTemplate( JDBCUtils.getDataSource() );

    @Override //IDEA快速生成需重写的方法
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        List<Category> list = null;
        try{ //涉及数据库查询结果时，总是：在外部新建实例对象；在try...catch中再赋值！
            list = template.query(
                    sql,
                    new BeanPropertyRowMapper<Category>(Category.class)
            );
        }catch( Exception e ){
            //不处理
        }

        return list;
    }
}
