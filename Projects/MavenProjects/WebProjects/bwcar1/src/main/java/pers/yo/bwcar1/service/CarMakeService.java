package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarMake;

import java.util.List;

public interface CarMakeService {
    public abstract int addCarMake(CarMake carMake);
    public abstract void delCarMake(Integer id);
    public abstract int updateCarMake(CarMake carMake);
    public abstract CarMake findById(Integer id);
    public abstract List<CarMake> findAll();
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
