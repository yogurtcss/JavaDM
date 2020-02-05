package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.CarParams;
import pers.yo.bwcar1.pojo.CarParamsExample;

public interface CarParamsMapper {
    int countByExample(CarParamsExample example);

    int deleteByExample(CarParamsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarParams record);

    int insertSelective(CarParams record);

    List<CarParams> selectByExample(CarParamsExample example);

    CarParams selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarParams record, @Param("example") CarParamsExample example);

    int updateByExample(@Param("record") CarParams record, @Param("example") CarParamsExample example);

    int updateByPrimaryKeySelective(CarParams record);

    int updateByPrimaryKey(CarParams record);
}