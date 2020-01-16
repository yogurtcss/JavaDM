package demo05_Query.dao;

import demo05_Query.domain.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 查询所有角色
     * @return
     */
    public abstract List<Role> findAll();
}
