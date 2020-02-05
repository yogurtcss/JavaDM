package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.pojo.MemberProfileLog;
import pers.yo.bwcar1.pojo.MemberProfileLogExample;

public interface MemberProfileLogMapper {
    int countByExample(MemberProfileLogExample example);

    int deleteByExample(MemberProfileLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberProfileLog record);

    int insertSelective(MemberProfileLog record);

    List<MemberProfileLog> selectByExample(MemberProfileLogExample example);

    MemberProfileLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberProfileLog record, @Param("example") MemberProfileLogExample example);

    int updateByExample(@Param("record") MemberProfileLog record, @Param("example") MemberProfileLogExample example);

    int updateByPrimaryKeySelective(MemberProfileLog record);

    int updateByPrimaryKey(MemberProfileLog record);
}