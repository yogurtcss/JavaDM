package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParamType;

import java.util.List;

public interface CarParamTypeService {
    public abstract int addCarParamType(CarParamType carParamType);
    public abstract void delCarParmType(Integer id);
    public abstract int updateCarParamType(CarParamType carParamType);
    public abstract CarParamType findById(Integer id);
    public abstract List<CarParamType> findAll();
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
