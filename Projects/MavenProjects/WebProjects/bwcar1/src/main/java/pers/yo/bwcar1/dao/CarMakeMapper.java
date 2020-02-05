package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.CarMake;
import pers.yo.bwcar1.pojo.CarMakeExample;

public interface CarMakeMapper {
    int countByExample(CarMakeExample example);

    int deleteByExample(CarMakeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarMake record);

    int insertSelective(CarMake record);

    List<CarMake> selectByExample(CarMakeExample example);

    CarMake selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarMake record, @Param("example") CarMakeExample example);

    int updateByExample(@Param("record") CarMake record, @Param("example") CarMakeExample example);

    int updateByPrimaryKeySelective(CarMake record);

    int updateByPrimaryKey(CarMake record);
}