package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;

public interface MenuService {
    //针对 bootstrap要求填入后端数据的格式，特地整出了这个DataGridResult类
    public abstract DataGridResult findMenu( QueryDTO queryDTO );
}
