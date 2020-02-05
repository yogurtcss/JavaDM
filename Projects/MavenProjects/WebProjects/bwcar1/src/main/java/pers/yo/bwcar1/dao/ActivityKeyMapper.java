package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.ActivityKey;
import pers.yo.bwcar1.pojo.ActivityKeyExample;

public interface ActivityKeyMapper {
    int countByExample(ActivityKeyExample example);

    int deleteByExample(ActivityKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityKey record);

    int insertSelective(ActivityKey record);

    List<ActivityKey> selectByExample(ActivityKeyExample example);

    ActivityKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityKey record, @Param("example") ActivityKeyExample example);

    int updateByExample(@Param("record") ActivityKey record, @Param("example") ActivityKeyExample example);

    int updateByPrimaryKeySelective(ActivityKey record);

    int updateByPrimaryKey(ActivityKey record);
}