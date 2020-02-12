package pers.yo.bwcar1.service;

import java.util.List;

public interface RoleService {

    public abstract List<String> findRolesByUserId( Long userId );
}
