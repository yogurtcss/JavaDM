package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysMenu;
import pers.yo.bwcar1.utils.R;

import java.util.List;

public interface MenuService {
    //针对 bootstrap要求填入后端数据的格式，特地整出了这个DataGridResult类
    public abstract DataGridResult findMenu( QueryDTO queryDTO );

    //新增方法
    public abstract R deleteMenu(List<Long> ids );

    //selectMenu()对应dao接口的方法：findMenu()
    public abstract R selectMenu();

    public abstract R saveMenu( SysMenu sysMenu );

    public abstract R findMenuById( Long menuId );

    public abstract R updateMenu( SysMenu sysMenu );

    public abstract List<String> findPermsByUserId( Long userId );

    public abstract R findUserMenu( Long userId );
}
