package com.mapper;

import com.pojo.Role;
import com.pojo.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    List<Role> findAllRole(Role role);

    void saveRole(Role role);

    void updateRole(Role role);

    List<Integer> findAllMenuByRoleId(Integer roleId);

    void RoleContextMenu(Role_menu_relation role_menu_relation);

    void deleteRoleContextMenu(Integer roleId);

    void deleteRole(Integer id);

}
