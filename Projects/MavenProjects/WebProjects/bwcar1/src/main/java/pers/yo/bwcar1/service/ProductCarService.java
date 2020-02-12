package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.ProductWithBLOBs;

public interface ProductCarService {

    public abstract int addProduct( ProductWithBLOBs productWithBLOBs );
    public abstract int delProduct(Integer id);
    public abstract int updateProduct( ProductWithBLOBs productWithBLOBs );
    public abstract ProductWithBLOBs findById( Integer id );
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
