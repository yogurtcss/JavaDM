package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.SdkInterface;
import pers.yo.bwcar1.pojo.SdkInterfaceExample;

public interface SdkInterfaceMapper {
    int countByExample(SdkInterfaceExample example);

    int deleteByExample(SdkInterfaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SdkInterface record);

    int insertSelective(SdkInterface record);

    List<SdkInterface> selectByExample(SdkInterfaceExample example);

    SdkInterface selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SdkInterface record, @Param("example") SdkInterfaceExample example);

    int updateByExample(@Param("record") SdkInterface record, @Param("example") SdkInterfaceExample example);

    int updateByPrimaryKeySelective(SdkInterface record);

    int updateByPrimaryKey(SdkInterface record);
}