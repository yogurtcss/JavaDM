package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.DictModel;
import pers.yo.bwcar1.pojo.DictModelExample;

public interface DictModelMapper {
    int countByExample(DictModelExample example);

    int deleteByExample(DictModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictModel record);

    int insertSelective(DictModel record);

    List<DictModel> selectByExample(DictModelExample example);

    DictModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictModel record, @Param("example") DictModelExample example);

    int updateByExample(@Param("record") DictModel record, @Param("example") DictModelExample example);

    int updateByPrimaryKeySelective(DictModel record);

    int updateByPrimaryKey(DictModel record);
}