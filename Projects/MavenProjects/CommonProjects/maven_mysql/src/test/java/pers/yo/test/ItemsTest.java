package pers.yo.test;

import org.junit.Test;
import pers.yo.dao.ItemsDao;
import pers.yo.dao.impl.ItemsDaoImpl;
import pers.yo.domain.Items;

import java.sql.SQLException;
import java.util.List;

public class ItemsTest {
    @Test
    public void findAll() throws SQLException {
        ItemsDao itDao = new ItemsDaoImpl(); //接口回调 ——向上转型
        List<Items> list = itDao.findAll();
        for( Items one : list ){
            System.out.println( "ID："+one.getId() );
            System.out.println( "Name："+one.getName() );
        }
    }
}
