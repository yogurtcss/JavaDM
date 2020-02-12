package pers.yo.bwcar1.service;

import org.apache.poi.ss.usermodel.Workbook;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    public abstract List<SysUser> findAll();

    //2020-02-08 13:32:12
    public abstract DataGridResult findUserByByPage( QueryDTO queryDTO );

    public abstract Workbook exportUser();

    public abstract SysUser findByUsername( String username );
}
