package pers.yo.bwcar1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysMenu;
import pers.yo.bwcar1.pojo.SysMenuExample;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /* 新增的方法findMenuByPage，已在 映射配置文件.xml中写好了
    * 返回值也是 pojo类 SysMenu，传入的形参是查询参数QueryDTO --数据传输对象
    *  */
    public abstract List<SysMenu> findMenuByPage( QueryDTO queryDTO );

    //新增的方法；List的泛型是包装类java.lang.Long 长整型
    public abstract int deleteMenu( List<Long> ids );

    public abstract List<SysMenu> findMenu();
}