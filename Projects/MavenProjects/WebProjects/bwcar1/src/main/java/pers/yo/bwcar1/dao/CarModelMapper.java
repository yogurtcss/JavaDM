package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.CarModel;
import pers.yo.bwcar1.pojo.CarModelExample;

public interface CarModelMapper {
    int countByExample(CarModelExample example);

    int deleteByExample(CarModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarModel record);

    int insertSelective(CarModel record);

    List<CarModel> selectByExample(CarModelExample example);

    CarModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarModel record, @Param("example") CarModelExample example);

    int updateByExample(@Param("record") CarModel record, @Param("example") CarModelExample example);

    int updateByPrimaryKeySelective(CarModel record);

    int updateByPrimaryKey(CarModel record);
}