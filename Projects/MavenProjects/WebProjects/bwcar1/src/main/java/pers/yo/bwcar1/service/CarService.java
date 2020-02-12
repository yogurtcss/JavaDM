package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Car;

public interface CarService {
    public abstract int addCar(Car car);
    public abstract int delCar(Integer id);
    public abstract int updateCar(Car car);
    public abstract Car findById(Integer id);
    public DataGridResult findByPage(QueryDTO queryDTO);
}
