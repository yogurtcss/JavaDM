package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParams;

public interface CarParamService {

    public abstract int addCarParams(CarParams carParams);
    public abstract void delCarParams(Integer id);
    public abstract int updateCarParams(CarParams carParams);
    public abstract CarParams findById(Integer id);
    public abstract DataGridResult findByPage(QueryDTO queryDTO);
}
