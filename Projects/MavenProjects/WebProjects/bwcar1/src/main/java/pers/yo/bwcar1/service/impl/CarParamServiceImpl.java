package pers.yo.bwcar1.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.CarParamsMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParams;
import pers.yo.bwcar1.pojo.CarParamsExample;
import pers.yo.bwcar1.service.CarParamService;

import java.util.List;

@Service
public class CarParamServiceImpl implements CarParamService {

    @Autowired
    private CarParamsMapper cpMapper;


    @Override
    public int addCarParams(CarParams carParams) {
        return cpMapper.insertSelective(carParams);
    }

    @Override
    public void delCarParams(Integer id) {
        cpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarParams(CarParams carParams) {
        return cpMapper.updateByPrimaryKeySelective(carParams);
    }

    @Override
    public CarParams findById(Integer id) {
        return cpMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarParamsExample example = new CarParamsExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort) ){
            example.setOrderByClause("id");
        }
        List<CarParams> carParams = cpMapper.selectByExample(example);
        PageInfo<CarParams> info = new PageInfo<>(carParams);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,carParams);
        return dgRst;
    }
}
