package pers.yo.bwcar1.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysUser;
import pers.yo.bwcar1.pojo.SysUserExample;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    //2020-02-08 13:31:22 新增
    public abstract List<SysUser> findByPage( QueryDTO queryDTO );

    public abstract List<Map<String,Object>> exportUser();

    //传入单个形参时，可以不加 @Param(XXX)注解
    public abstract SysUser findByUsername( String username );


}