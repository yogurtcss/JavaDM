package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.CarMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Car;
import pers.yo.bwcar1.pojo.CarExample;
import pers.yo.bwcar1.service.CarService;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper cMapper;

    @Override
    public int addCar(Car car) {
        return cMapper.insertSelective(car);
    }

    @Override
    public int delCar(Integer id) {
        return cMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCar(Car car) {
        return cMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public Car findById(Integer id) {
        return cMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarExample example = new CarExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort)){
            example.setOrderByClause("id");
        }
        List<Car> cs = cMapper.selectByExample(example);
        PageInfo<Car> info = new PageInfo<>(cs);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,cs);
        return dgRst;
    }
}
