package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarManufacturer;

public interface CarMenuFacturerService {
    public abstract int addCarMenuFacturer( CarManufacturer carManufacturer );
    public abstract void delCarMenu( Integer id );
    public abstract int updateCarManuFacturer( CarManufacturer carManufacturer );
    public abstract CarManufacturer findById( Integer id );
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
