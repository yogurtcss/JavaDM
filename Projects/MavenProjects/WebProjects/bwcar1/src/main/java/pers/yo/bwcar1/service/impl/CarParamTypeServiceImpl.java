package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.bwcar1.dao.CarParamTypeMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParamType;
import pers.yo.bwcar1.pojo.CarParamTypeExample;
import pers.yo.bwcar1.service.CarParamTypeService;

import java.util.List;

@Service
public class CarParamTypeServiceImpl implements CarParamTypeService {

    @Autowired
    private CarParamTypeMapper cptMapper;

    @Override
    public int addCarParamType(CarParamType carParamType) {
        return( cptMapper.insertSelective(carParamType) );
    }

    @Override
    public void delCarParmType(Integer id) {
        cptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarParamType(CarParamType carParamType) {
        return cptMapper.updateByPrimaryKeySelective(carParamType);
    }

    @Override
    public CarParamType findById(Integer id) {
        return cptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarParamType> findAll() {
        return cptMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(),queryDTO.getLimit() );
        CarParamTypeExample example = new CarParamTypeExample();
        if( (queryDTO.getSort()!=null)&&(!queryDTO.getSort().equals("")) ){
            example.setOrderByClause("id");
        }
        List<CarParamType> cpts = cptMapper.selectByExample(example);
        PageInfo<CarParamType> info = new PageInfo<>(cpts); //包装分页对象
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult( total,cpts );
        return dgRst;
    }
}
