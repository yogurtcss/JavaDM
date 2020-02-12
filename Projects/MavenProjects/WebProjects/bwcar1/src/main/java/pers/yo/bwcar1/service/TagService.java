package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Tag;
import pers.yo.bwcar1.utils.R;

public interface TagService {

    public abstract int addTag( Tag tag );
    public abstract void delTag( Integer id );
    public abstract int updateTag( Tag tag );
    public abstract Tag findById( Integer id );
    public abstract DataGridResult findByPage( QueryDTO queryDTO );

    public abstract R findLineData();
    public abstract R findBarData();
    public abstract R findPieData();


}
