package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.ActivityInterface;
import pers.yo.bwcar1.pojo.ActivityInterfaceExample;

public interface ActivityInterfaceMapper {
    int countByExample(ActivityInterfaceExample example);

    int deleteByExample(ActivityInterfaceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityInterface record);

    int insertSelective(ActivityInterface record);

    List<ActivityInterface> selectByExample(ActivityInterfaceExample example);

    ActivityInterface selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityInterface record, @Param("example") ActivityInterfaceExample example);

    int updateByExample(@Param("record") ActivityInterface record, @Param("example") ActivityInterfaceExample example);

    int updateByPrimaryKeySelective(ActivityInterface record);

    int updateByPrimaryKey(ActivityInterface record);
}