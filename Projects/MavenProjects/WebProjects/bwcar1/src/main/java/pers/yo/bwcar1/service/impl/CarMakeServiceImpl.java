package pers.yo.bwcar1.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.bwcar1.dao.CarMakeMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarMake;
import pers.yo.bwcar1.pojo.CarMakeExample;
import pers.yo.bwcar1.service.CarMakeService;

import java.util.List;


@Service //记得要加这个注解！
public class CarMakeServiceImpl implements CarMakeService {

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public int addCarMake(CarMake carMake) {
        return( carMakeMapper.insertSelective(carMake) );
    }

    @Override
    public void delCarMake(Integer id) {
        carMakeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarMake(CarMake carMake) {
        return( carMakeMapper.updateByPrimaryKeySelective(carMake) );
    }

    @Override
    public CarMake findById(Integer id) {
        return( carMakeMapper.selectByPrimaryKey(id) );
    }

    @Override
    public List<CarMake> findAll() {
        return( carMakeMapper.selectByExample(null) );
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(),queryDTO.getLimit() );
        CarMakeExample example = new CarMakeExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort)){
            example.setOrderByClause("id");
        }
        List<CarMake> carMakes = carMakeMapper.selectByExample(example);
        PageInfo<CarMake> info = new PageInfo<>(carMakes);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult( total, carMakes );
        return dgRst;
    }
}
