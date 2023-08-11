package com.service;

import com.pojo.Role;
import com.pojo.RoleMenuVo;
import com.pojo.Role_menu_relation;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole(Role role);

    void saveRole(Role role);

    void updateRole(Role role);

    List<Integer> findAllMenuByRoleId(Integer roleId);

    void RoleContextMenu(RoleMenuVo roleMenuVo);
}
