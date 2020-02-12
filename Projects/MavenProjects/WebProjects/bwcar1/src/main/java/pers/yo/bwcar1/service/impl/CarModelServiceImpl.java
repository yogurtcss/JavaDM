package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.CarModelMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarModel;
import pers.yo.bwcar1.pojo.CarModelExample;
import pers.yo.bwcar1.service.CarModelService;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelMapper cmMapper;


    @Override
    public int addCarModel(CarModel carModel) {
        return cmMapper.insertSelective(carModel);
    }

    @Override
    public int delCarModel(Integer id) {
        return cmMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarModel(CarModel carModel) {
        return cmMapper.updateByPrimaryKeySelective(carModel);
    }

    @Override
    public CarModel findById(Integer id) {
        return cmMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        CarModelExample example = new CarModelExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort)){
            example.setOrderByClause("id");
        }
        List<CarModel> cms = cmMapper.selectByExample(example);
        PageInfo<CarModel> info = new PageInfo<>(cms);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,cms);
        return dgRst;
    }
}
