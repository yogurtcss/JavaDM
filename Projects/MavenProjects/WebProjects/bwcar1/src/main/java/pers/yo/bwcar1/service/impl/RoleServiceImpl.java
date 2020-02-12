package pers.yo.bwcar1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.bwcar1.dao.SysRoleMapper;
import pers.yo.bwcar1.service.RoleService;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findRolesByUserId(Long userId) {
        return( sysRoleMapper.findRolesByUserId(userId) );
    }
}
