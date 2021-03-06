package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.DictBrandPoint;
import pers.yo.bwcar1.pojo.DictBrandPointExample;

public interface DictBrandPointMapper {
    int countByExample(DictBrandPointExample example);

    int deleteByExample(DictBrandPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictBrandPoint record);

    int insertSelective(DictBrandPoint record);

    List<DictBrandPoint> selectByExample(DictBrandPointExample example);

    DictBrandPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictBrandPoint record, @Param("example") DictBrandPointExample example);

    int updateByExample(@Param("record") DictBrandPoint record, @Param("example") DictBrandPointExample example);

    int updateByPrimaryKeySelective(DictBrandPoint record);

    int updateByPrimaryKey(DictBrandPoint record);
}