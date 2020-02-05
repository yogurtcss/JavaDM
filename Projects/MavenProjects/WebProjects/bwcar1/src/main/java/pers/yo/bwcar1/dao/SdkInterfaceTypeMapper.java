package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.SdkInterfaceType;
import pers.yo.bwcar1.pojo.SdkInterfaceTypeExample;

public interface SdkInterfaceTypeMapper {
    int countByExample(SdkInterfaceTypeExample example);

    int deleteByExample(SdkInterfaceTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SdkInterfaceType record);

    int insertSelective(SdkInterfaceType record);

    List<SdkInterfaceType> selectByExample(SdkInterfaceTypeExample example);

    SdkInterfaceType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SdkInterfaceType record, @Param("example") SdkInterfaceTypeExample example);

    int updateByExample(@Param("record") SdkInterfaceType record, @Param("example") SdkInterfaceTypeExample example);

    int updateByPrimaryKeySelective(SdkInterfaceType record);

    int updateByPrimaryKey(SdkInterfaceType record);
}