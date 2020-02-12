package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.CarManufacturerMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarManufacturer;
import pers.yo.bwcar1.pojo.CarManufacturerExample;
import pers.yo.bwcar1.service.CarMenuFacturerService;

import java.util.List;

@Service
public class CarMenuFacturerServiceImpl implements CarMenuFacturerService {

    @Autowired
    private CarManufacturerMapper cmfMapper;


    @Override
    public int addCarMenuFacturer(CarManufacturer carManufacturer) {
        return cmfMapper.insertSelective(carManufacturer);
    }

    @Override
    public void delCarMenu(Integer id) {
        cmfMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarManuFacturer(CarManufacturer carManufacturer) {
        return cmfMapper.updateByPrimaryKeySelective(carManufacturer);
    }

    @Override
    public CarManufacturer findById(Integer id) {
        return cmfMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(),queryDTO.getLimit() );
        CarManufacturerExample example = new CarManufacturerExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort) ){
            example.setOrderByClause("id");
        }
        List<CarManufacturer> cmfs = cmfMapper.selectByExample(example);
        PageInfo<CarManufacturer> info = new PageInfo<>(cmfs);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,cmfs);
        return dgRst;
    }
}
