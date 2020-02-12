package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarModel;

public interface CarModelService {
    public abstract int addCarModel(CarModel carModel);
    public abstract int delCarModel(Integer id);
    public abstract int updateCarModel(CarModel carModel);
    public abstract CarModel findById( Integer id );
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
